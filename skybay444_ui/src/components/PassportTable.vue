
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Passports</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalPassports = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalPassports">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add Passport</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="PassportId" type="text" placeholder="Enter PassportId" v-model="passportToAdd.passportId"></base-input>
  <base-input label="Number" type="text" placeholder="Enter Number" v-model="passportToAdd.number"></base-input>
  <base-input label="Country" type="text" placeholder="Enter Country" v-model="passportToAdd.country"></base-input>
  <base-input label="ExpirationDate" type="text" placeholder="Enter ExpirationDate" v-model="passportToAdd.expirationDate"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="passports" :row-key="record => record.PassportId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <PassportPictureView :passports="passports" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import PassportService from "../services/PassportService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import PassportPictureView from './PassportPictureView.vue';


const passportsColumns = [
  "passportId",
  "year",
  "date",
  "competitionId",
  "passportId"
]

export default {
  props: {
    passports: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    PassportPictureView
  },

  data() {
    return {
      modalPassports: false,
        isTableView: true,

      columns: [
        {
          title: 'Passport Id',
		dataIndex: 'passportId',
          visible: true,
          scopedSlots: { customRender: 'passportId' },
          sorter: true
          //sorter: (a, b) => a.passportId - b.passportId,
          //sorter: (a, b) => a.passportId.localeCompare(b.passportId),
        },
        {
          title: 'Number',
		dataIndex: 'number',
          visible: true,
          scopedSlots: { customRender: 'number' },
          sorter: true
          //sorter: (a, b) => a.number - b.number,
          //sorter: (a, b) => a.number.localeCompare(b.number),
        },
        {
          title: 'Country',
		dataIndex: 'country',
          visible: true,
          scopedSlots: { customRender: 'country' },
          sorter: true
          //sorter: (a, b) => a.country - b.country,
          //sorter: (a, b) => a.country.localeCompare(b.country),
        },
        {
          title: 'Expiration Date',
		dataIndex: 'expirationDate',
          visible: true,
          scopedSlots: { customRender: 'expirationDate' },
          sorter: true
          //sorter: (a, b) => a.expirationDate - b.expirationDate,
          //sorter: (a, b) => a.expirationDate.localeCompare(b.expirationDate),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} passports`,
      },

      passports: [],
      passportToAdd: {},

      passportsTable: {
        columns: [...passportsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'passportId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderPassportsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let passportsTableData = [];
      for (let i = 0; i < this.passports.length; i++) {
        passportsTableData.push({
          id: i,
          passportId: this.passports[i].passportId,
          year: this.passports[i].year,
          date: this.passports[i].date,
          competitionId: this.passports[i].competitionId,
          passportId: this.passports[i].passportId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-passports',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToFlightDetail(id) {
      this.$router.push({ name: 'FlightDetail', params: { flightId: id.toString() }})
    },
    routingToAircraftDetail(id) {
      this.$router.push({ name: 'AircraftDetail', params: { aircraftId: id.toString() }})
    },
    routingToPassengerDetail(id) {
      this.$router.push({ name: 'PassengerDetail', params: { passengerId: id.toString() }})
    },
    routingToBookingDetail(id) {
      this.$router.push({ name: 'BookingDetail', params: { bookingId: id.toString() }})
    },
    routingToCrewDetail(id) {
      this.$router.push({ name: 'CrewDetail', params: { crewId: id.toString() }})
    },
    routingToAirportDetail(id) {
      this.$router.push({ name: 'AirportDetail', params: { airportId: id.toString() }})
    },
    routingToAirlineDetail(id) {
      this.$router.push({ name: 'AirlineDetail', params: { airlineId: id.toString() }})
    },
    routingToTicketDetail(id) {
      this.$router.push({ name: 'TicketDetail', params: { ticketId: id.toString() }})
    },
    routingToScheduleDetail(id) {
      this.$router.push({ name: 'ScheduleDetail', params: { scheduleId: id.toString() }})
    },
    routingToRouteDetail(id) {
      this.$router.push({ name: 'RouteDetail', params: { routeId: id.toString() }})
    },
    routingToBaggageDetail(id) {
      this.$router.push({ name: 'BaggageDetail', params: { baggageId: id.toString() }})
    },
    routingToPassportDetail(id) {
      this.$router.push({ name: 'PassportDetail', params: { passportId: id.toString() }})
    },
    routingToMealDetail(id) {
      this.$router.push({ name: 'MealDetail', params: { mealId: id.toString() }})
    },
    routingToSeatDetail(id) {
      this.$router.push({ name: 'SeatDetail', params: { seatId: id.toString() }})
    },
    routingToLoyaltyDetail(id) {
      this.$router.push({ name: 'LoyaltyDetail', params: { loyaltyId: id.toString() }})
    },
    routingToOfferDetail(id) {
      this.$router.push({ name: 'OfferDetail', params: { offerId: id.toString() }})
    },
    routingToCheckInDetail(id) {
      this.$router.push({ name: 'CheckInDetail', params: { checkInId: id.toString() }})
    },
    routingToGateDetail(id) {
      this.$router.push({ name: 'GateDetail', params: { gateId: id.toString() }})
    },
    routingToAnnouncementDetail(id) {
      this.$router.push({ name: 'AnnouncementDetail', params: { announcementId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForPassportsChanged', this.searchQuery);
		//this.renderPassportsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalPassports = false;

      const currentDate = new Date().getTime();
      this.passportToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.passportToAdd);
      console.log(jsonData);
      
      const res = await PassportService.addPassport(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderPassportsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
