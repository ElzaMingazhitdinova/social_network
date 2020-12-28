package com.example.testingrestdocs.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Post {
    private Long id;
    private String text;
    private Long userid;
    private Date date;
    private String material;
    //private ArrayList<String> materials;
    // private HashMap<Characteristic, ArrayList<String>> marks;
    //public ArrayList<Comment> comments;
    private boolean blocked; // сделать проверку на то что пост заблокирован и что его ен надо воозращать

    public Long getIdUser() {
        return userid;
    }

    public void setUser(Long userId) {
        this.userid = userId;
    }

    public Post(Long id, Long userId, String text, Date date, boolean blocked, String material) {
        this.id = id;
        this.userid = userId;
        this.text = text;
        this.date = date;
        this.blocked = false;
        this.material = material;
    }
    //  materials = new ArrayList<>();
    //  marks = new HashMap<>();
    //  for (Characteristic characteristic : category.getCharacteristics()) {
    //     marks.put(characteristic, new ArrayList<>());
//comments = new ArrayList<>();


   /* public void setCategory(Category category) {
        for (Characteristic characteristic : this.category.getCharacteristics()) {
            //    marks.remove(characteristic);
        }
        this.category = category;
    }*/

   /*public ArrayList<String> getMaterials() {
        return materials;
    }

   /* public void setMaterials(ArrayList<String> materials) {
        this.materials = materials;
    }

    public void addMaterial(String material) {
        materials.add(material);
    }

    public HashMap<Characteristic, ArrayList<String>> getMarks() {
        return marks;
    }

    public void addMark(Characteristic characteristic, String mark) {
        if (marks.get(characteristic) == null) {
            throw new IllegalArgumentException(String.format("Characteristic:%s does not belong to this post",
                    characteristic.getName()));
        }
        if (characteristic.getVariants().contains(mark)) {
            marks.get(characteristic).add(mark);
        } else {
            ArrayList<String> marksArray = marks.remove(characteristic);
            try {
                characteristic.addVariant(mark);
            } catch (Exception e) {
                marks.put(characteristic, marksArray);
                throw e;
            }
            marksArray.add(mark);
            marks.put(characteristic, marksArray);
        }
    }

    public ArrayList<Characteristic> getCharacteristics() {
        return new ArrayList<Characteristic>(marks.keySet());
    }

    public void addCharacteristic(Characteristic characteristic) {
        marks.computeIfAbsent(characteristic, k -> new ArrayList<String>());
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }*/

    public boolean isBlocked() {
        return blocked;
    }

    public void setId() {
        this.id = id;
    }

    public void setText() {
        this.text = text;
    }

    public void setMaterial() {
        this.material = material;
    }

    public Date getDatetime() {
        return date;
    }
    public String getMaterial() {
        return material;
    }

    public Long getId() {
        return id;
    }

    public Date getDateTime() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
