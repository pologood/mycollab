DROP TABLE IF EXISTS `m_crm_accounts_leads`;
DROP TABLE IF EXISTS `m_crm_campaigns_accounts`;
DROP TABLE IF EXISTS `m_crm_campaigns_contacts`;
DROP TABLE IF EXISTS `m_crm_campaigns_leads`;
DROP TABLE IF EXISTS `m_crm_contacts_cases`;
DROP TABLE IF EXISTS `m_crm_contacts_leads`;
DROP TABLE IF EXISTS `m_crm_contacts_opportunities`;
DROP TABLE IF EXISTS `m_crm_meeting_invitees`;
DROP TABLE IF EXISTS `m_crm_opportunities_leads`;
DROP TABLE IF EXISTS `m_crm_product_catalog`;
DROP TABLE IF EXISTS `m_crm_task`;
DROP TABLE IF EXISTS `m_crm_target_list`;
DROP TABLE IF EXISTS `m_crm_product`;
DROP TABLE IF EXISTS `m_crm_quote_group_product`;
DROP TABLE IF EXISTS `m_crm_target`;
DROP TABLE IF EXISTS `m_crm_quote`;
DROP TABLE IF EXISTS `m_crm_notifications`;
DROP TABLE IF EXISTS `m_crm_meeting`;
DROP TABLE IF EXISTS `m_crm_customer`;
DROP TABLE IF EXISTS `m_crm_contract`;
DROP TABLE IF EXISTS `m_crm_call`;
DROP TABLE IF EXISTS `m_crm_case`;
DROP TABLE IF EXISTS `m_crm_lead`;
DROP TABLE IF EXISTS `m_crm_contact`;
DROP TABLE IF EXISTS `m_crm_opportunity`;
DROP TABLE IF EXISTS `m_crm_campaign`;
ALTER TABLE `m_crm_account` RENAME TO  `m_customer` ;
DROP TABLE IF EXISTS `m_ecm_activity_log`;
DROP TABLE IF EXISTS `m_ecm_driveinfo`;
DROP TABLE IF EXISTS `m_ecm_external_drive`;
DROP TABLE IF EXISTS `m_group_user`;
DROP TABLE IF EXISTS `m_group`;
DROP TABLE IF EXISTS `m_prj_predecessor`;