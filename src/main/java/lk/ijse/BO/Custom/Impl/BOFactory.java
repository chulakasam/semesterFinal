package lk.ijse.BO.Custom.Impl;

import lk.ijse.DAO.Custom.Impl.*;

import lk.ijse.DAO.SuperDAO;



public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory =new BOFactory() : boFactory;
    }
    public enum BOTypes{
        CLIENT,CONFIRMORDER,DASHBOARD,ITEM,ORDER,PAYMENTS,SUPPLIER,TRAINER,TRAINERCLIENT,WORKOUT
    }
    public SuperDAO getBO(BOFactory.BOTypes boTypes){
        switch (boTypes){
            case CLIENT:
                return new ClientBOImpl();
            case CONFIRMORDER:
                return new ConfirmOrderBOImpl();
            case DASHBOARD:
                return  new DashboardBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case PAYMENTS:
                return new PaymentBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case TRAINER:
                return new TrainerBOImpl();
            case TRAINERCLIENT:
                return new TrainerClientBOImpl();
                case WORKOUT:
                return new WorkoutBOImpl();
                default:
                     return null;
        }
    }
}


