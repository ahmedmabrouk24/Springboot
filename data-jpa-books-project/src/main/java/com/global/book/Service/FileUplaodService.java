package com.global.book.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.util.IOUtils;
import com.global.book.Entity.Author;
import com.global.book.Entity.Book;
import com.global.book.Error.FileStorageException;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class FileUplaodService {

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookService bookService;

	Logger log = LoggerFactory.getLogger(FileUplaodService.class);

	//@Value("${file.upload.base-path}")
	private final String basePath = "E:\\Spring github\\Springboot\\data-jpa-books-project\\global\\book\\";

	//@Value("${google.storage.bucket-name}")
	private String googleBucketName = "";

	//@Value("${google.storage.project-id}")
	private String projectId = "";

	//@Value("${google.storage.credentials.path}")
	private String credentialPath = "";

	//@Value("${aws.s3.bucket}")
	private String awsBucketName;

	private AmazonS3 amazonS3;

	private Path fileStorageLocation;

	public String storeFile(File file, Long id, String pathType) {

		// create uploaded path
		this.fileStorageLocation = Paths.get(basePath + pathType).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}

		String fileName = StringUtils.cleanPath(id + "-" + file.getName());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			InputStream targetStream = new FileInputStream(file);
			Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
			updateImagePath(id, fileStorageLocation.toString());
			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public void updateImagePath(Long id, String pathType) {
		if (pathType.contains("authors")) { // update author image
			Author author = authorService.findById(id);
			author.setPathImage(pathType);
			authorService.update(author);
		} else { // update book icon
			Book book = bookService.findById(id);
			book.setBookIcon(pathType);
			bookService.update(book);
		}
	}

	public File convertMultiPartFileToFile(final MultipartFile multipartFile) {
		final File file = new File(multipartFile.getOriginalFilename());
		try (final FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(multipartFile.getBytes());
		} catch (final IOException ex) {
			log.error("Error converting the multi-part file to file= ", ex.getMessage());
		}
		return file;
	}

	public String cloudUploadFile(MultipartFile file, Long id, String pathType) {

		String fileName = null;

		if (file.getContentType().contains("image")) {
			fileName = id + "_" + UUID.randomUUID() + ".jpg";
		} else {
			fileName = id + file.getOriginalFilename();
		}
		String uniqueFileName = pathType + fileName;
		try {

			awsUploadObject(uniqueFileName, file);
			updateImagePath(id, basePath + pathType);

		} catch (Exception e) {
			e.printStackTrace();
			throw new FileStorageException("Error converting the multi-part file to file = ", e);
		}

		return fileName;
	}

	public void awsUploadObject(final String uniqueFileName, final MultipartFile multipartFile) {

		log.info("Uploading file with name= " + uniqueFileName);

		try {

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(IOUtils.toByteArray(multipartFile.getInputStream()).length);

			final PutObjectRequest putObjectRequest = new PutObjectRequest(awsBucketName, uniqueFileName,
					multipartFile.getInputStream(), meta).withCannedAcl(CannedAccessControlList.PublicRead);

			PutObjectResult result = amazonS3.putObject(putObjectRequest);
			log.info("File uploaded successfully result" + result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void awsDeleteObject(String fileUrl) {
		final DeleteObjectRequest req = new DeleteObjectRequest(awsBucketName, fileUrl);
		amazonS3.deleteObject(req);
		log.info("File deleted from bucket " + awsBucketName + " as " + fileUrl);
	}

	public void googelUploadObject(String projectId, String objectName, MultipartFile file) throws IOException {
		InputStream inputStream = getClass().getResourceAsStream(this.credentialPath);
		Credentials credentials = GoogleCredentials.fromStream(inputStream);

		Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build()
				.getService();
		BlobId blobId = BlobId.of(googleBucketName, objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();

//	    storage.create(blobInfo,Files.readAllBytes(Paths.get(filePath)));

		storage.create(blobInfo, file.getInputStream());
//		storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

		log.info("File uploaded to bucket " + googleBucketName + " as " + objectName);

	}

	public void googleDeleteObject(String imagePath) throws IOException {
		InputStream inputStream = getClass().getResourceAsStream(this.credentialPath);
		Credentials credentials = GoogleCredentials.fromStream(inputStream);

		Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build()
				.getService();
		BlobId blobId = BlobId.of(googleBucketName, imagePath);
		storage.delete(blobId);

		log.info("File deleted from bucket " + googleBucketName + " as " + imagePath);

	}
}
