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
            <passport-table
            v-if="passports && passports.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:passports="passports"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-passports="getAllPassports"
             >

            </passport-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PassportTable from "@/components/PassportTable";
import PassportService from "../services/PassportService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PassportTable,
  },
  data() {
    return {
      passports: [],
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
    async getAllPassports(sortBy='passportId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PassportService.getAllPassports(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.passports.length) {
					this.passports = response.data.passports;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching passports:", error);
        }
        
      } catch (error) {
        console.error("Error fetching passport details:", error);
      }
    },
  },
  mounted() {
    this.getAllPassports();
  },
  created() {
    this.$root.$on('searchQueryForPassportsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPassports();
    })
  }
};
</script>
<style></style>
