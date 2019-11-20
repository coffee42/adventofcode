package cz.binaryfree.advent01.calibrator

import spock.lang.Specification
import spock.lang.Unroll

class IteratorCalibratorSpec extends Specification {


    def "input values must not be null"() {
        when:
            new IterationDeviceCalibrator(null)
        then :
            thrown IllegalStateException
    }
    @Unroll
    def "resulting frequency expected to equals #freq with given starting frequency #startFreq and change #change"() {
        given :
            def deviceCalibrator =  new IterationDeviceCalibrator([])
        expect:
           freq == deviceCalibrator.compute(startFreq, change)

        where:
            startFreq | change || freq
            0  | 0  || 0
            0  | 0  || 0
            0  | 1  || 1
            0  | -1 || -1
            1  | -1 || 0
            -1 | 1  || 0
            1  | 2  || 3

    }

    @Unroll
    def "resulting frequency expected to equal #result"() {
        given:
            def deviceCalibrator = new IterationDeviceCalibrator(input)
        expect:
            deviceCalibrator.calibrate() == result
        where:
          input << [[+1, -1],
                    [+3, +3, +4, -2, -4],
                    [-6, +3, +8, +5, -6],
                    [+7, +7, -2, -7, -4]]
          result << [0, 10, 5, 14]
    }

}
