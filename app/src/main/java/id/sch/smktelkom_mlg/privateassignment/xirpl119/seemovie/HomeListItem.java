package id.sch.smktelkom_mlg.privateassignment.xirpl119.seemovie;

/**
 * Created by A455L on 14/05/2017.
 */
public class HomeListItem {
    private String imageUrl;
    private  String title;
    private String content;

    public HomeListItem(String imageUrl, String title, String content){
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
}
