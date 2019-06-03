package pl.karol.equipy.asssignment;



public class AssignmentMapper {

    static AssignmentDto toDto(Assignment assignment) {
        AssignmentDto assignmentDto = new AssignmentDto();
        assignmentDto.setId(assignment.getId());
        assignmentDto.setStart(assignment.getStart());
        assignmentDto.setEnd(assignment.getEnd());
        assignmentDto.setUserId(assignment.getUser().getId());
        assignmentDto.setAssetId(assignment.getAsset().getId());
        return assignmentDto;
    }
}
