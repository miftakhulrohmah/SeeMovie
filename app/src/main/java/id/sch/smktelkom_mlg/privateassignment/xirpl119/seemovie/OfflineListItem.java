package id.sch.smktelkom_mlg.privateassignment.xirpl119.seemovie;

import java.io.Serializable;

/**
 * Created by A455L on 14/05/2017.
 */
public class OfflineListItem implements Serializable {
        public String imageUrl;
        public String head;
        // private String desc;

                public OfflineListItem(String imageUrl, String head) {
                this.imageUrl = imageUrl;
                this.head = head;

                    }



            }