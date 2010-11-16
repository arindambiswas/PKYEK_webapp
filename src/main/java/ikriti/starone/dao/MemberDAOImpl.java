package ikriti.starone.dao;

import ikriti.starone.dao.BaseDAO;
import ikriti.starone.dao.interfaces.MemberDAO;
import ikriti.starone.hb.Member;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * The @Repository allows Spring to recognize this as a managed component (so we
 * don't need to specify it in XML) and also tells spring to do DAO exception
 * translation to the Spring exception hierarchy.
 * 
 * 
 */
@Repository
public class MemberDAOImpl extends BaseDAO<Member, Long> implements MemberDAO
{

}
