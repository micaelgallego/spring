package es.urjc.code.items;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemRestController {
	
	@Autowired
	private ItemRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Item> findItems() {
		return repository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Item> addItem(@RequestBody Item item) {
		item.setId(null);
		Item newItem = repository.saveAndFlush(item);
		return new ResponseEntity<>(newItem,HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Item> updateItem(@RequestBody Item updatedItem,
			@PathVariable Integer id) {
		
		updatedItem.setId(id);
		Item item = repository.saveAndFlush(updatedItem);
		return new ResponseEntity<>(item,HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Integer id) {
		repository.delete(id);
	}
}
