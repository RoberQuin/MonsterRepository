package com.example.mosntruosapi.controller;

import com.example.mosntruosapi.model.Monster;
import com.example.mosntruosapi.repository.MonsterRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monsters")
public class MonsterController {
	@Autowired
	private MonsterRepository monsterRepository;
	
	@GetMapping
	public List<Monster> getAllMonsters(){
		return monsterRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Monster> getMonsterById(@PathVariable Long id){
		return monsterRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Monster createMonster(@RequestBody Monster monster) {
		return monsterRepository.save(monster);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Monster> updateMonster(@PathVariable Long id, @RequestBody Monster monsterDetails){
		return monsterRepository.findById(id).map(monster -> {
			monster.setName(monsterDetails.getName());
			monster.setType(monsterDetails.getType());
			return ResponseEntity.ok(monsterRepository.save(monster));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMonster(@PathVariable Long id){
		if(monsterRepository.existsById(id)) {
			monsterRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
