package ikriti.starone.dao.interfaces;

import ikriti.starone.hb.FbUser;
import ikriti.starone.hb.Member;

import com.trg.dao.hibernate.GenericDAO;

/**
 * <p>
 * As a matter of best practice other components reference this interface rather
 * than the implementation of the DAO itself.
 * 
 */
public interface FbUserDAO extends GenericDAO<FbUser, Long>
{

}
