package pl.karol.equipy.user;

import org.springframework.stereotype.Component;
import pl.karol.equipy.asssignment.Assignment;

@Component
public class UserAssignmentMapper {

    public static UserAssignmentDto toDto(Assignment assignment) {
        UserAssignmentDto userAssignmentDto = new UserAssignmentDto();
        userAssignmentDto.setId(userAssignmentDto.getId());
        userAssignmentDto.setStart(assignment.getStart());
        userAssignmentDto.setEnd(assignment.getEnd());
        userAssignmentDto.setAssetId(assignment.getAsset().getId());
        userAssignmentDto.setAssetName(assignment.getAsset().getName());
        userAssignmentDto.setAssetSerialNumber(assignment.getAsset().getSerialNumber());
        return userAssignmentDto;
    }
}
