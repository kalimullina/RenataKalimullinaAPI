package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BoardDto {

    private String id;
    private String name;
    private String desc;
    private String descData;
    private boolean closed;
    private String idOrganization;
    private String idEnterprise;
    private boolean pinned;
    private String url;
    private String shortUrl;
    private Prefs prefs;
    private String shortLink;
    private Map<String, String> labelNames;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static final class Prefs {
        private String permissionLevel;
        private boolean hideVotes;
        private String voting;
        private String comments;
        private String invitations;
        private boolean selfJoin;
        private boolean cardCovers;
        private boolean isTemplate;
        private String cardAging;
        private boolean calendarFeedEnabled;
        private String background;
        private String backgroundImage;
        private String backgroundImageScaled;
        private boolean backgroundTile;
        private String backgroundBrightness;
        private String backgroundColor;
        private String backgroundBottomColor;
        private String backgroundTopColor;
        private boolean canBePublic;
        private boolean canBeEnterprise;
        private boolean canBeOrg;
        private boolean canBePrivate;
        private boolean canInvite;

    }
}
