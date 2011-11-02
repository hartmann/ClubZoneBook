from net.grinder.script.Grinder import grinder
from net.grinder.script import Test

from no.bekk.webdriver import EventWebTest

class TestRunner:

    def __init__(self):
        self.originalWebtest = EventWebTest()

    def __call__(self):
        grinder.statistics.delayReports = 1

        log = grinder.logger.output
        out = grinder.logger.TERMINAL

        testMethods = ["shouldAddEventToEventlist", "shouldUpdateEventWithLocation", "shouldDeleteEvent", ]

        testNumber = 0
        for test in testMethods:
            log(test, out)
            testNumber += 1
            testCase = Test(testNumber, test).wrap(self.originalWebtest)
            self.originalWebtest.setUp()
            getattr(testCase, test)()
            self.originalWebtest.tearDown()


