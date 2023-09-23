package hackathon.project.demoservice.client;

import hackathon.project.demoservice.domain.Tier;
import hackathon.project.demoservice.domain.ZResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("CONTENTSERVICE")
public interface ContentServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "api/v1/tiers/default", consumes = "application/json")
    ZResponse<List<Tier>> insertDefaultTires(
            @RequestParam(name = "professionId", required = false) Long professionId);


}