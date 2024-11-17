import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Flights from  '@/pages/Flights.vue';
import FlightDetail from  '@/pages/FlightDetail.vue';
import Aircrafts from  '@/pages/Aircrafts.vue';
import AircraftDetail from  '@/pages/AircraftDetail.vue';
import Passengers from  '@/pages/Passengers.vue';
import PassengerDetail from  '@/pages/PassengerDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Crews from  '@/pages/Crews.vue';
import CrewDetail from  '@/pages/CrewDetail.vue';
import Airports from  '@/pages/Airports.vue';
import AirportDetail from  '@/pages/AirportDetail.vue';
import Airlines from  '@/pages/Airlines.vue';
import AirlineDetail from  '@/pages/AirlineDetail.vue';
import Tickets from  '@/pages/Tickets.vue';
import TicketDetail from  '@/pages/TicketDetail.vue';
import Schedules from  '@/pages/Schedules.vue';
import ScheduleDetail from  '@/pages/ScheduleDetail.vue';
import Routes from  '@/pages/Routes.vue';
import RouteDetail from  '@/pages/RouteDetail.vue';
import Baggages from  '@/pages/Baggages.vue';
import BaggageDetail from  '@/pages/BaggageDetail.vue';
import Passports from  '@/pages/Passports.vue';
import PassportDetail from  '@/pages/PassportDetail.vue';
import Meals from  '@/pages/Meals.vue';
import MealDetail from  '@/pages/MealDetail.vue';
import Seats from  '@/pages/Seats.vue';
import SeatDetail from  '@/pages/SeatDetail.vue';
import Loyaltys from  '@/pages/Loyaltys.vue';
import LoyaltyDetail from  '@/pages/LoyaltyDetail.vue';
import Offers from  '@/pages/Offers.vue';
import OfferDetail from  '@/pages/OfferDetail.vue';
import CheckIns from  '@/pages/CheckIns.vue';
import CheckInDetail from  '@/pages/CheckInDetail.vue';
import Gates from  '@/pages/Gates.vue';
import GateDetail from  '@/pages/GateDetail.vue';
import Announcements from  '@/pages/Announcements.vue';
import AnnouncementDetail from  '@/pages/AnnouncementDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/flights',
																			  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/flights',
		name: 'Flights',
		layout: DefaultLayout,
		component: Flights,
	},
	{
	    path: '/flight/:flightId', 
	    name: 'FlightDetail',
		layout: DefaultLayout,
	    component: FlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/aircrafts',
		name: 'Aircrafts',
		layout: DefaultLayout,
		component: Aircrafts,
	},
	{
	    path: '/aircraft/:aircraftId', 
	    name: 'AircraftDetail',
		layout: DefaultLayout,
	    component: AircraftDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passengers',
		name: 'Passengers',
		layout: DefaultLayout,
		component: Passengers,
	},
	{
	    path: '/passenger/:passengerId', 
	    name: 'PassengerDetail',
		layout: DefaultLayout,
	    component: PassengerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crews',
		name: 'Crews',
		layout: DefaultLayout,
		component: Crews,
	},
	{
	    path: '/crew/:crewId', 
	    name: 'CrewDetail',
		layout: DefaultLayout,
	    component: CrewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airports',
		name: 'Airports',
		layout: DefaultLayout,
		component: Airports,
	},
	{
	    path: '/airport/:airportId', 
	    name: 'AirportDetail',
		layout: DefaultLayout,
	    component: AirportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airlines',
		name: 'Airlines',
		layout: DefaultLayout,
		component: Airlines,
	},
	{
	    path: '/airline/:airlineId', 
	    name: 'AirlineDetail',
		layout: DefaultLayout,
	    component: AirlineDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tickets',
		name: 'Tickets',
		layout: DefaultLayout,
		component: Tickets,
	},
	{
	    path: '/ticket/:ticketId', 
	    name: 'TicketDetail',
		layout: DefaultLayout,
	    component: TicketDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/schedules',
		name: 'Schedules',
		layout: DefaultLayout,
		component: Schedules,
	},
	{
	    path: '/schedule/:scheduleId', 
	    name: 'ScheduleDetail',
		layout: DefaultLayout,
	    component: ScheduleDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/routes',
		name: 'Routes',
		layout: DefaultLayout,
		component: Routes,
	},
	{
	    path: '/route/:routeId', 
	    name: 'RouteDetail',
		layout: DefaultLayout,
	    component: RouteDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/baggages',
		name: 'Baggages',
		layout: DefaultLayout,
		component: Baggages,
	},
	{
	    path: '/baggage/:baggageId', 
	    name: 'BaggageDetail',
		layout: DefaultLayout,
	    component: BaggageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passports',
		name: 'Passports',
		layout: DefaultLayout,
		component: Passports,
	},
	{
	    path: '/passport/:passportId', 
	    name: 'PassportDetail',
		layout: DefaultLayout,
	    component: PassportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/meals',
		name: 'Meals',
		layout: DefaultLayout,
		component: Meals,
	},
	{
	    path: '/meal/:mealId', 
	    name: 'MealDetail',
		layout: DefaultLayout,
	    component: MealDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/seats',
		name: 'Seats',
		layout: DefaultLayout,
		component: Seats,
	},
	{
	    path: '/seat/:seatId', 
	    name: 'SeatDetail',
		layout: DefaultLayout,
	    component: SeatDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/loyaltys',
		name: 'Loyaltys',
		layout: DefaultLayout,
		component: Loyaltys,
	},
	{
	    path: '/loyalty/:loyaltyId', 
	    name: 'LoyaltyDetail',
		layout: DefaultLayout,
	    component: LoyaltyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/offers',
		name: 'Offers',
		layout: DefaultLayout,
		component: Offers,
	},
	{
	    path: '/offer/:offerId', 
	    name: 'OfferDetail',
		layout: DefaultLayout,
	    component: OfferDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/checkIns',
		name: 'CheckIns',
		layout: DefaultLayout,
		component: CheckIns,
	},
	{
	    path: '/checkIn/:checkInId', 
	    name: 'CheckInDetail',
		layout: DefaultLayout,
	    component: CheckInDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/gates',
		name: 'Gates',
		layout: DefaultLayout,
		component: Gates,
	},
	{
	    path: '/gate/:gateId', 
	    name: 'GateDetail',
		layout: DefaultLayout,
	    component: GateDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/announcements',
		name: 'Announcements',
		layout: DefaultLayout,
		component: Announcements,
	},
	{
	    path: '/announcement/:announcementId', 
	    name: 'AnnouncementDetail',
		layout: DefaultLayout,
	    component: AnnouncementDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
