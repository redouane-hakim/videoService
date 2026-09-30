package ma.xproce.videoservice.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoResponse {
    private Long id;
    private String name;
    private String url;
    private String description;
    private String datePublication;
    private CreatorResponse creator;
}