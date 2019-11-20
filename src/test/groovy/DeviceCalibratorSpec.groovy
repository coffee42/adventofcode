
package cz.binaryfree.advent01.command;
import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Stream

class DeviceCalibratorSpec extends Specification {


    def "input values must not be null"() {
        when:
            new DeviceCalibrator(null)
        then :
            thrown IllegalStateException
    }
    @Unroll
    def "resulting frequency expected to equals #freq with given starting frequency #startFreq and change #change"() {
        given :
            def deviceCalibrator =  new DeviceCalibrator([])
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

    @Unroll
    def "resulting frequency expected to equal #result"() {
        given:
            def deviceCalibrator = new DeviceCalibrator(input)
        expect:
            deviceCalibrator.calibrate() == result
        where:
          input << [[+1, -2, +3, +1],
                    [+1, +1, -2],
                    [-1, -2, -3]]
          result << [3, 0, -6]
    }

}
