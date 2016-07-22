package es.urjc.code.daw.tablonanuncios;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TablonController {

	// Si se abre la URL http://127.0.0.1:8080/h2-console y se configura
	// la URL JDBC con el valor jdbc:h2:mem:testdb se puede acceder a la
	// base de datos de la aplicación

	@Autowired
	private AnunciosRepository repository;

	@PostConstruct
	public void init() {
		for (int i = 0; i < 100; i++) {
			repository.save(new Anuncio("Pepe", "Anuncio" + i + " Pepe", "XXXX"));
			repository.save(new Anuncio("Juan", "Anuncio" + i + " Juan", "XXXX"));
			repository.save(new Anuncio("Luis", "Anuncio" + i + " Pepe", "XXXX"));
		}
	}

	@RequestMapping("/")
	public String tablon(Model model, Pageable page) {

		String sortedBy;
		if (page.getSort() != null) {
			sortedBy = page.getSort().iterator().next().getProperty();
		} else {
			sortedBy = "nombre";
			page = new PageRequest(page.getPageNumber(), page.getPageSize(),
					new Sort(new Order(Sort.DEFAULT_DIRECTION, "nombre")));
		}

		Page<Anuncio> anuncios = repository.findAll(page);

		model.addAttribute("anuncios", anuncios);		
		model.addAttribute("sortedBy", sortedBy);

		model.addAttribute("showNext", !anuncios.isLast());
		model.addAttribute("showPrev", !anuncios.isFirst());
		model.addAttribute("numPage", anuncios.getNumber());
		model.addAttribute("nextPage", anuncios.getNumber() + 1);
		model.addAttribute("prevPage", anuncios.getNumber() - 1);

		return "tablon";
	}

	@RequestMapping("/anuncio/nuevo")
	public String nuevoAnuncio(Model model, Anuncio anuncio) {

		repository.save(anuncio);

		return "anuncio_guardado";

	}

	@RequestMapping("/anuncio/{id}")
	public String verAnuncio(Model model, @PathVariable long id) {

		Anuncio anuncio = repository.findOne(id);

		model.addAttribute("anuncio", anuncio);

		return "ver_anuncio";
	}
}