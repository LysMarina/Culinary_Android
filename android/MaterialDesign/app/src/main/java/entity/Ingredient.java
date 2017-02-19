package entity;

/**
 * Created by leych on 15.02.2017.
 */

public class Ingredient {
        private String id;
        private String name;
        private String count;



    public Ingredient (){
        this.id ="id";
        this.name="name";
        this.count="0";
    }
    public Ingredient (String name,String count){
      //  this.id =id;
        this.name=name;
        this.count=count;
    }
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

