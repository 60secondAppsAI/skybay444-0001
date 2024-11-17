<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <loyalty-table
            v-if="loyaltys && loyaltys.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:loyaltys="loyaltys"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-loyaltys="getAllLoyaltys"
             >

            </loyalty-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import LoyaltyTable from "@/components/LoyaltyTable";
import LoyaltyService from "../services/LoyaltyService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    LoyaltyTable,
  },
  data() {
    return {
      loyaltys: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllLoyaltys(sortBy='loyaltyId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await LoyaltyService.getAllLoyaltys(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.loyaltys.length) {
					this.loyaltys = response.data.loyaltys;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching loyaltys:", error);
        }
        
      } catch (error) {
        console.error("Error fetching loyalty details:", error);
      }
    },
  },
  mounted() {
    this.getAllLoyaltys();
  },
  created() {
    this.$root.$on('searchQueryForLoyaltysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllLoyaltys();
    })
  }
};
</script>
<style></style>
