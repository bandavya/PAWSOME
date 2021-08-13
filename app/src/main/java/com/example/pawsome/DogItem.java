package com.example.pawsome;

public class DogItem {
    private final String dImageUrl;
    private String dBreed;
    private String dLife_span;
    private String dWeight;
    private String dHeight;
    private String dTemp;



    public DogItem(String imageUrl, String breed, String Life_span,String Weight, String Height, String Temp){
        dImageUrl = imageUrl;
        dBreed = breed;
        dLife_span = Life_span;
        dWeight = Weight;
        dHeight = Height;
        dTemp = Temp;




    }

    public String getImageUrl(){
        return dImageUrl;
    }

    public String getBreedName(){
        return dBreed;
    }
    public String getLife_span(){
        return dLife_span;
    }
    public String getWeight(){
        return dWeight;
    }
    public String getHeight(){
        return dHeight;
    }
    public String getTemp(){
        return dTemp;
    }






}
