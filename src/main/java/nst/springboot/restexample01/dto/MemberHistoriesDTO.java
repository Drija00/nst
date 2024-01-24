package nst.springboot.restexample01.dto;

import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import nst.springboot.restexample01.controller.domain.SecretaryHistory;

import java.util.List;

public class MemberHistoriesDTO {
    List<ATHDto> athDtos;
    List<SecretaryHistoryDTO> secretaryHistoryDTOS;
    List<HeadHistoryDTO> headHistoryDTOS;

    public MemberHistoriesDTO(List<ATHDto> athDtos, List<SecretaryHistoryDTO> secretaryHistoryDTOS, List<HeadHistoryDTO> headHistoryDTOS) {
        this.athDtos = athDtos;
        this.secretaryHistoryDTOS = secretaryHistoryDTOS;
        this.headHistoryDTOS = headHistoryDTOS;
    }

    public List<ATHDto> getAthDtos() {
        return athDtos;
    }

    public void setAthDtos(List<ATHDto> athDtos) {
        this.athDtos = athDtos;
    }

    public List<SecretaryHistoryDTO> getSecretaryHistoryDTOS() {
        return secretaryHistoryDTOS;
    }

    public void setSecretaryHistoryDTOS(List<SecretaryHistoryDTO> secretaryHistoryDTOS) {
        this.secretaryHistoryDTOS = secretaryHistoryDTOS;
    }

    public List<HeadHistoryDTO> getHeadHistoryDTOS() {
        return headHistoryDTOS;
    }

    public void setHeadHistoryDTOS(List<HeadHistoryDTO> headHistoryDTOS) {
        this.headHistoryDTOS = headHistoryDTOS;
    }
}
