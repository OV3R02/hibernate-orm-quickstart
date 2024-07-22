package comm.primeurad.arches.library.application.vo;

import comm.primeurad.arches.library.domain.dto.EntAuthor;

import java.math.BigDecimal;

public class VOBook {

    private String id;
    private String title;
    private String pub_house;
    private String type;
    private BigDecimal price;
    private String description;
    private String voAuthor;

    public VOBook() {
    }

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

    public String getVoAuthor() {
        return voAuthor;
    }

    public void setVoAuthor(String voAuthor) {
        this.voAuthor = voAuthor;
    }
}
