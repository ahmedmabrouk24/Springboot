package com.global.book.Base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseService<T extends BaseEntity<ID>, ID extends Number> {
	@Autowired
	private BaseRepository<T, ID> baseRepository;
	
	public T findById(ID id) {
		return baseRepository.findById(id).orElseThrow();
	}
	public T findByName(String name) {
		return baseRepository.findByName(name);
	}
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(baseRepository.findAll());
	}
	public ResponseEntity<?> insert(T entity) {
		return ResponseEntity.ok(baseRepository.save(entity));
	}
	public ResponseEntity<?> update(T entity) {
		return ResponseEntity.ok(baseRepository.save(entity));
	}
	public void delete(ID id) {
		baseRepository.deleteById(id);	
	}
}
