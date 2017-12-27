package hu.stewemetal.pogoraidr.endpoint

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.github.messenger4j.Messenger
import hu.stewemetal.pogoraidr.model.CreateRaidData
import okhttp3.Response
import okhttp3.ResponseBody
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMethod

@RestController
@RequestMapping("/raid")
class RaidCreation(val messenger: Messenger) {
    private val logger = LoggerFactory.getLogger(RaidCreation::class.java)

    @RequestMapping(method = [RequestMethod.POST])
    fun createRaid(@RequestBody raidData: CreateRaidData): ResponseEntity<CreateRaidData> {
        logger.info("Received raid creation data: " + raidData)
        return ResponseEntity.ok(raidData)
    }
}