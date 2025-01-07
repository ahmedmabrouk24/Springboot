package com.global.book.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;

import com.global.book.error.RecordNotFoundException;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseService<T extends BaseEntity<ID>, ID extends Number> {
	@Autowired
	private BaseRepository<T, ID> baseRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	public T findById(ID id) {
		Optional<T> entity = baseRepository.findById(id);
		if (entity.isPresent()) {
			return baseRepository.findById(id).get();
		}
		else {
			String [] msgParam = {id.toString()};
			String msg = messageSource.getMessage("jakarta.validation.constraints.recored-not-found.message", msgParam, LocaleContextHolder.getLocale());
			throw new RecordNotFoundException(msg);
		}
		
	}
	public T findByName(String name) {
		return baseRepository.findByName(name);
	}
	public List<T> findAll() {
		return baseRepository.findAll();
	}
	public ResponseEntity<?> insert(T entity) {
		return ResponseEntity.ok(baseRepository.save(entity));
	}
	public ResponseEntity<?> insertAll(List<T> entity) {
		return ResponseEntity.ok(baseRepository.saveAll(entity));
	}
	public ResponseEntity<?> update(T entity) {
		return ResponseEntity.ok(baseRepository.save(entity));
	}
	public void delete(ID id) {
		baseRepository.deleteById(id);	
	}
}
