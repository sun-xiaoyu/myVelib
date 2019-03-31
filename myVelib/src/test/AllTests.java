package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AvoidPlusStationTest.class, BicycleTest.class, CardTest.class, CurrentDistributionTest.class,
		EBikeTest.class, FastPathToDestinationTest.class, GPSTest.class, MapTest.class, MapTest2.class, MBikeTest.class,
		MinWalDisTest.class, NoCardVisitorTest.class, OngoingRideTest.class, PreferPlusStationTest.class,
		PreservationOfUniformityTest.class, RecordTest.class, RequestTest.class, ServerTest.class, SlotTest.class,
		SolutionTest.class, StationTest.class, UserTest.class, VlibreCardVisitorTest.class, VmaxCardVisitorTest.class })
public class AllTests {

}
