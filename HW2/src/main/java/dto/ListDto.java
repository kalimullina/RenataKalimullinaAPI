package dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ListDto {

    private String id;
    private String name;
    private String idBoard;
    private List<CardDto> cards = new ArrayList<>();
    private boolean closed;
    private boolean subscribed;
    private int pos;
}
