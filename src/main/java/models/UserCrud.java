package models;

import services.hibernate.HibernateFacade;

public class UserCrud {
    public User findByEmail(String email) {

        HibernateFacade hibernateFacade = HibernateFacade.getInstance();

        return (User) hibernateFacade.getAllWhere(User.class, "email", email).get(0);

    }

    public boolean updateUser(User newUser) {

//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            String hql = "UPDATE User SET email = :email, firstName = :first_name, lastName = :last_name, birthDate = :birth_date, phone = :phone WHERE uuid = :uuid";
//            
//            Query query = session.createQuery(hql);
//            query.setParameter("email", newUser.getEmail());
//            query.setParameter("uuid", newUser.getUuid());
//            query.setParameter("first_name", newUser.getFirstName());
//            query.setParameter("last_name", newUser.getLastName());
//            query.setParameter("phone", newUser.getPhone());
//            query.setParameter("birth_date", newUser.getBirthDate());
//            query.executeUpdate();
//
//            transaction.commit();
//
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//                e.printStackTrace();
//            }
//
//            return false;
//        }

        HibernateFacade hibernateFacade  = HibernateFacade.getInstance();

        hibernateFacade.update(newUser);


        return true;

    }
}
