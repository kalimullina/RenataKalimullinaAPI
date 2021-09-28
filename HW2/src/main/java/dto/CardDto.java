package dto;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CardDto {

    private String id;
    private String name;
    private String idList;
    private String desc;
    private String url;
    private String idBoard;
    private String idShort;
    private String idAttachmentCover;
    private String shortLink;
    private String shortUrl;
    private boolean closed;
    private boolean manualCoverAttachment;
    private boolean subscribed;
    private Date due;
    private Date dateLastActivity;
    private List<String> idMembers;
    private List<String> idChecklists;
    private List<String> idMembersVoted;
    private int pos;
}
