package at.nullphilippexception.pastimeapplication

import at.nullphilippexception.pastimeapplication.EHobbyTypes.*
import org.junit.Test

class HobbyApiTest {
    @Test
    fun GIVEN_apiIsCalled_WHEN_responseIsReceived_THEN_itIsNotNull() {
        // given + when
        val apiResponse = HobbyApi().getRandomHobby()

        // then
        assert(apiResponse.activity != null)
        assert(apiResponse.type != null)
        assert(apiResponse.participants != null)
        assert(apiResponse.accessibility != null)
        assert(apiResponse.price != null)
    }

    @Test
    fun GIVEN_activityTypeIsSelected_WHEN_apiIsCalled_THEN_activityOfCorrectTypeIsReturned() {
        // given
        val testedType = CHARITY

        // when
        val apiResponse = HobbyApi().getTypedHobby(testedType)

        // then
        assert(EHobbyTypes.valueOf(apiResponse.type!!.uppercase()) == testedType)
    }
}