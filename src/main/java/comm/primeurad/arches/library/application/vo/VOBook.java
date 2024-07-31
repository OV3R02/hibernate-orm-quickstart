package comm.primeurad.arches.library.application.vo;

import java.math.BigDecimal;

public class VOBook {

    // Variables of VOBook class

    private String id;
    private String title;
    private String pubHouse;
    private String type;
    private BigDecimal price;
    private String description;
    private VOAuthor voAuthor;

    // getters and setters of VOBook class

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VOAuthor getVoAuthor() {
        return voAuthor;
    }

    public void setVoAuthor(VOAuthor voAuthor) {
        this.voAuthor = voAuthor;
    }
}
