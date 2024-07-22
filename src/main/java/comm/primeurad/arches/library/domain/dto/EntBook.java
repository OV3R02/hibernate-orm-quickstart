package comm.primeurad.arches.library.domain.dto;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "known_books")
@NamedQuery(name = "Books.findAll", query = "SELECT b FROM EntBook b ORDER BY b.title", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class EntBook {

    @Id
    @SequenceGenerator(name = "bookSequence", sequenceName = "known_fruits_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "bookSequence")
    private Integer id;

    @Column(length = 60, unique = true)
    private String title;

    @Column(length = 100, nullable = false)
    private String pubHouse;

    @Column(length = 20, nullable = false)
    private String type;

    @Column(length = 5, nullable = false)
    private BigDecimal price;

    @JoinColumn(name = "id")
    private EntAuthor entAuthor;

    public EntBook(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubHouse() {
        return pubHouse;
    }

    public void setPubHouse(String pubHouse) {
        this.pubHouse = pubHouse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public EntAuthor getEntAuthor() {
        return entAuthor;
    }

    public void setEntAuthor(EntAuthor entAuthor) {
        this.entAuthor = entAuthor;
    }

    @Override
    public String toString() {
        return "EntBook{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pubHouse='" + pubHouse + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", entAuthor=" + entAuthor +
                '}';
    }
}
