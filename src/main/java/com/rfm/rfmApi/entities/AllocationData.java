package com.rfm.rfmApi.entities;

public class AllocationData {

    private String ctsEmpID;
    private Resource resource;
    private Allocation allocation;

    /**
     * @return the ctsEmpID
     */
    public String getCtsEmpID() {
        return ctsEmpID;
    }
    /**
     * @param ctsEmpID the ctsEmpID to set
     */
    public void setCtsEmpID(String ctsEmpID) {
        this.ctsEmpID = ctsEmpID;
    }

    /**
     * @return the resource
     */
    public Resource getResource() {
        return resource;
    }
    /**
     * @param resource the resource to set
     */
    public void setResource(Resource resource) {
        this.resource = resource;
    }
    /**
     * @return the allocation
     */
    public Allocation getAllocation() {
        return allocation;
    }
    /**
     * @param allocation the allocation to set
     */
    public void setAllocation(Allocation allocation) {
        this.allocation = allocation;
    }

}
