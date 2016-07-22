import {Injectable} from 'angular2/core';
import {Observable} from 'rxjs/Observable';
import {withObserver} from './utils';

export class Book {

  constructor(
    public id: number,
    public title: string,
    public abstract: string) {}

}

@Injectable()
export class BookService {

  private books = [
  	new Book(1, 'SUEÑOS DE ACERO Y NEON', 'Los personajes que protagonizan este relato sobreviven en una sociedad en decadencia a la que, no obstante, lograrán devolver la posibilidad de un futuro. Año 2484. En un mundo dominado por las grandes corporaciones, solo un hombre, Jordi Thompson, detective privado deslenguado y vividor, pero de gran talento y sentido d...'),
  	new Book(2, 'LA VIDA SECRETA DE LA MENTE','La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.'),
  	new Book(3, 'CASI SIN QUERER','El amor algunas veces es tan complicado como impredecible. Pero al final lo que más valoramos son los detalles más simples, los más bonitos, los que llegan sin avisar. Y a la hora de escribir sobre sentimientos, no hay nada más limpio que hacerlo desde el corazón. Y eso hace Defreds en este libro.'),
  	new Book(4, 'TERMINAMOS Y OTROS POEMAS SIN TERMINAR','Recopilación de nuevos poemas, textos en prosa y pensamientos del autor. Un sabio dijo una vez: «Pocas cosas hipnotizan tanto en este mundo como una llama y como la luna, será porque no podemos cogerlas o porque nos iluminan en la penumbra». Realmente no sé si alguien dijo esta cita o me la acabo de inventar pero deberían de haberla escrito porque el poder hipnótico que ejercen esa mujer de rojo y esa dama blanca sobre el ser humano es digna de estudio.'),
  	new Book(5, 'LA LEGIÓN PERDIDA','En el año 53 a. C. el cónsul Craso cruzó el Éufrates para conquistar Oriente, pero su ejército fue destrozado en Carrhae. Una legión entera cayó prisionera de los partos. Nadie sabe a ciencia cierta qué pasó con aquella legión perdida.150 años después, Trajano está a punto de volver a cruzar el Éufrates. ...')
  ];

  getBooks() {
    return withObserver(this.books);
  }

  getBook(id: number | string) {
    let book = this.books.filter(h => h.id === +id)[0]
    return withObserver(new Book(book.id, book.title, book.abstract));
  }

  removeBook(book: Book){
    for(let i=0; i<this.books.length; i++){
        if(this.books[i].id === book.id){
          this.books.splice(i,1);
          break;
        }
    }
    return withObserver(undefined);
  }

  saveBook(book: Book){
    if(book.id){
      let oldBook = this.books.filter(h => h.id === book.id)[0];
      oldBook.title = book.title;
      oldBook.abstract = book.abstract;
    } else {
      book.id = this.books.length+1;
      this.books.push(book);
    }
    return withObserver(book);
  }
}
