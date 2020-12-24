package com.example.testingrestdocs.objects;

import java.util.ArrayList;


public class Category {
    private Long id;
    private String name;
    private String description;
    // private ArrayList<Post> posts;
    private ArrayList<Characteristic> characteristics;

    public Category(Long id, String name, String description, ArrayList characteristics ) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.characteristics = new ArrayList<>();
        // this.posts = new ArrayList<>();
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category other = (Category) o;
        return name.equals(other.name) &&
               description.equals(other.description) &&
               posts.equals(other.posts) &&
               characteristics.equals(other.characteristics);
    }*/

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   /* public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) throws IllegalArgumentException {
        for (Post post : posts) {
            if (!equals(post.getCategory())) {
                throw new IllegalArgumentException("Post category not equals this category");
            }
        }
        this.posts = posts;
    }

    public void addPost(Post post) throws IllegalArgumentException {
        if (post.getCategory() != this) {
            throw new IllegalArgumentException("Post category not equals this category");
        }
        posts.add(post);
    }*/

    public ArrayList<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(ArrayList<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public void addCharacteristic(Characteristic characteristic) {
        characteristics.add(characteristic);
    }
}

