package comm.primeurad.arches.library.domain.dto;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "known_authors")
@NamedQuery(name = "Author.findAll", query = "SELECT a FROM EntAuthor a ORDER BY a.name", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class EntAuthor {

    @Id
    @SequenceGenerator(name = "authorSequence", sequenceName = "known_authors_id_seq", initialValue = 10, allocationSize = 1)
    @GeneratedValue(generator = "authorSequence")
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String surname;

    @Column(length = 3, nullable = false)
    private LocalDate birthdate;

    @Column(length = 6)
    private String sex;

    @Column(length = 100, nullable = false)
    private String mail;

    @Column(length = 13)
    private String number;

   // @ManyToOne
   // ArrayList<EntBook> entBooksList;

    public EntAuthor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "EntAuthor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", sex='" + sex + '\'' +
                ", mail='" + mail + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
