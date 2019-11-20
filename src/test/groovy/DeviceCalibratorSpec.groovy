
package cz.binaryfree.advent01.command;
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Stream

class DeviceCalibratorSpec extends Specification {


    def "input values must not be null"() {
        when:
            DeviceCalibrator.builder().build()
        then :
            thrown IllegalStateException
    }
    @Unroll
    def "result frequency should be == #freq with given starting frequency #startFreq and change #change"() {
        given :
        def deviceCalibrator =  DeviceCalibrator.builder()
                .input(Stream.of(1,2,3,4,5))
                .build()
        expect:
           freq == deviceCalibrator.compute(startFreq, change)

        where:
            startFreq | change || freq
            0 | 0 || 0
            0 | 0 || 0
            0 | 1 || 1
            0 | -1 || -1
            1 | -1 || 0
            -1 | 1 || 0
            1 | 2 || 3

    }

}
