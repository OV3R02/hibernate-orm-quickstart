package comm.primeurad.arches.library.application.vo;

import java.math.BigDecimal;
import java.util.List;

public class VOBook {

    // Variables of VOBook class

    private String idBook;
    private String title;
    private String pub_house;
    private String type;
    private BigDecimal price;
    private String description;
    private List<VOAuthor> voAuthorList;

    // getters and setters of VOBook class

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPub_house() {
        return pub_house;
    }

    public void setPub_house(String pub_house) {
        this.pub_house = pub_house;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<VOAuthor> getVoAuthorList() {
        return voAuthorList;
    }

    public void setVoAuthorList(List<VOAuthor> voAuthorList) {
        this.voAuthorList = voAuthorList;
    }
}
