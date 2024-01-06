package lk.ijse.DAO;

import lk.ijse.DAO.Custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null) ? daoFactory =new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
       CLIENT,ITEM,SUPPLIER,TRAINER,ORDER,ORDER_DETAILS,USER,WORKOUT,TRAINERCLIENT,PAYMENT
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CLIENT:
                return new ClientDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case TRAINER:
                return new TrainerDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailDAOImpl();
            case USER:
                return new UserDAOImpl();
            case WORKOUT:
                return new WorkoutDAOImpl();
            case TRAINERCLIENT:
                return new TrainerClientDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            default:
                return null;
        }
    }
}
