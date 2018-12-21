package com.mycollab.common.dao;

import com.mycollab.common.domain.SaveSearchResult;
import com.mycollab.common.domain.SaveSearchResultExample;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface SaveSearchResultMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    long countByExample(SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int deleteByExample(SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int insert(SaveSearchResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int insertSelective(SaveSearchResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    List<SaveSearchResult> selectByExampleWithBLOBs(SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    List<SaveSearchResult> selectByExample(SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    SaveSearchResult selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByExampleSelective(@Param("record") SaveSearchResult record, @Param("example") SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") SaveSearchResult record, @Param("example") SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByExample(@Param("record") SaveSearchResult record, @Param("example") SaveSearchResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByPrimaryKeySelective(SaveSearchResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(SaveSearchResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByPrimaryKey(SaveSearchResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    Integer insertAndReturnKey(SaveSearchResult value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_save_search_result
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    void massUpdateWithSession(@Param("record") SaveSearchResult record, @Param("primaryKeys") List primaryKeys);
}