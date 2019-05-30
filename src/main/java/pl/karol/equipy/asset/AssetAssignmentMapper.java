package pl.karol.equipy.asset;

import pl.karol.equipy.asssignment.Assignment;

public class AssetAssignmentMapper {

    public static AssetAssignmentDto toDto(Assignment assignment) {
        AssetAssignmentDto assetAssignmentDto = new AssetAssignmentDto();
        assetAssignmentDto.setId(assignment.getId());
        assetAssignmentDto.setStart(assignment.getStart());
        assetAssignmentDto.setEnd(assignment.getEnd());
        assetAssignmentDto.setUserId(assignment.getUser().getId());
        assetAssignmentDto.setFirstName(assignment.getUser().getFirstName());
        assetAssignmentDto.setLastName(assignment.getUser().getLastName());
        assetAssignmentDto.setPesel(assignment.getUser().getPesel());
        return assetAssignmentDto;
    }
}
