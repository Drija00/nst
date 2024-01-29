package nst.springboot.restexample01.dto;

import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import nst.springboot.restexample01.controller.domain.SecretaryHistory;

import java.util.List;

public class MemberHistoriesDTO {
    List<ATHDto> academicTitleHistories;
    List<SecretaryHistoryDTO> secretaryHistories;
    List<HeadHistoryDTO> headHistories;

    public MemberHistoriesDTO(List<ATHDto> academicTitleHistories, List<SecretaryHistoryDTO> secretaryHistories, List<HeadHistoryDTO> headHistories) {
        this.academicTitleHistories = academicTitleHistories;
        this.secretaryHistories = secretaryHistories;
        this.headHistories = headHistories;
    }

    public List<ATHDto> getAcademicTitleHistories() {
        return academicTitleHistories;
    }

    public void setAcademicTitleHistories(List<ATHDto> academicTitleHistories) {
        this.academicTitleHistories = academicTitleHistories;
    }

    public List<SecretaryHistoryDTO> getSecretaryHistories() {
        return secretaryHistories;
    }

    public void setSecretaryHistories(List<SecretaryHistoryDTO> secretaryHistories) {
        this.secretaryHistories = secretaryHistories;
    }

    public List<HeadHistoryDTO> getHeadHistories() {
        return headHistories;
    }

    public void setHeadHistories(List<HeadHistoryDTO> headHistories) {
        this.headHistories = headHistories;
    }

    @Override
    public String toString() {
        return "MemberHistoriesDTO{" +
                "academicTitleHistories =" + academicTitleHistories +
                ", secretaryHistories =" + secretaryHistories +
                ", headHistories =" + headHistories +
                '}';
    }
}
