package hu.stewemetal.pogoraidr.model

import com.fasterxml.jackson.annotation.JsonCreator

data class CreateRaidRequestData @JsonCreator constructor(val host:String, val gymId:Int, val date:String,
                                 val time:String)