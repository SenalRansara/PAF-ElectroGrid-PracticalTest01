package java.com.electrogrid.power_consumption.resource;

import java.com.electrogrid.power_consumption.model.PowerConsumption;
import java.com.electrogrid.power_consumption.repository.PowerConsumptionRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

@Path("/powerCon")
public class PowerConsumptionResource {
    //creating object for store data
    PowerConsumptionRepository repo = new PowerConsumptionRepository();

    // read data from data base - Read Operation
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //public Response getPowerConsumptions() {
    public List <PowerConsumption> getPowerConsumptions() {
        System.out.println("Get Power Consumption Details");

        //System.out.println(repo.getPowerConsumptions() + "message");
        return repo.getPowerConsumptions();
    }

    //if you need to have a success messages for get operation as well


//        String msg = "";
//        if(!repo.getPowerConsumptions().isEmpty()){
//            msg = "Data Retrieve Successfully";
//        }else{
//            msg = "Error !";
//        }
//        return Response.status(Response.Status.OK).entity(msg + repo.getPowerConsumptions()) .type(MediaType.APPLICATION_JSON) .build();
//    }

    // read data from data base - Search Operation
    @GET
    @Path("power_con/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PowerConsumption getPowerConsumption(@PathParam("id") int id) {

        return repo.getPowerConsumption(id);

    }

    // adding data to data base - Create Operation
    @POST
    @Path("power_con")
    @Produces(MediaType.APPLICATION_JSON)
//    public PowerConsumption createPowerConsumption(PowerConsumption pc1){
//
//        System.out.println(pc1);
//        repo.createPowerConsumption(pc1);
//        return pc1;
//
//    }
    public Response createPowerConsumption(PowerConsumption pc1){

        System.out.println(pc1);
        String res = repo.createPowerConsumption(pc1);
        return Response.status(Response.Status.OK).entity(res) .type(MediaType.APPLICATION_JSON) .build();

    }


    // update data details from data base - Update Operation
    @PUT
    @Path("power_con")
    @Produces(MediaType.APPLICATION_JSON)
//    public PowerConsumption updatePowerConsumption(PowerConsumption pc1){
//
//        System.out.println(pc1);
//        if (repo.getPowerConsumption(pc1.getId()).getId()==0) {
//            repo.createPowerConsumption(pc1);
//        }
//        else{
//            repo.updatePowerConsumption(pc1);
//        }
//        return pc1;
//    }

    public Response updatePowerConsumption(PowerConsumption pc1){

        System.out.println(pc1);
        String mg = "";
        if (repo.getPowerConsumption(pc1.getId()).getId()==0) {
            mg = repo.updatePowerConsumption(pc1);

        }
        else{
            mg = repo.updatePowerConsumption(pc1);

        }
        return Response.status(Response.Status.OK).entity(mg) .type(MediaType.APPLICATION_JSON) .build();

    }



    //delete data entry from data base - Delete Operation
    @DELETE
    @Path("power_con/{id}")
    @Produces(MediaType.APPLICATION_JSON)
//    public PowerConsumption deletePowerConsumption(@PathParam("id") int id){
//
//        PowerConsumption pc = repo.getPowerConsumption(id);
//        if (pc.getId()!=0){
//            repo.deletePowerConsumption(id);
//        }
//        return pc;
//    }

    public Response deletePowerConsumption(@PathParam("id") int id){

        PowerConsumption pc = repo.getPowerConsumption(id);
        String res = "";
        if (pc.getId()!=0){
            res = repo.deletePowerConsumption(id);
            //return Response.status(Response.Status.OK).entity(res) .type(MediaType.APPLICATION_JSON) .build();
        }
        return Response.status(Response.Status.OK).entity(res) .type(MediaType.APPLICATION_JSON) .build();

    }


}
