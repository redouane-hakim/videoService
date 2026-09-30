package ma.xproce.videoservice.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatorResponse {
    private Long id;
    private String name;
    private String email;
}