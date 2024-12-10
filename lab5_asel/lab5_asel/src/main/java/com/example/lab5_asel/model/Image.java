//package com.example.lab5_asel.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "images")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Image {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "imageId")
//    private Long imageId;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "originalFileName")
//    private String originalFileName;
//
//    @Column(name = "size")
//    private Long size;
//
//    @Column(name = "contentType")
//    private String contentType;
//
//    @Column(name = "isPreviewImage")
//    private boolean isPreviewImage;
//
//    @Lob
//    private byte[] bytes;
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    private Users user;
//
//
//
//
//}
