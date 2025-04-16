CREATE OR REPLACE FUNCTION log_all_table_changes()
RETURNS trigger AS $$
BEGIN
INSERT INTO nbp_log(action_name, table_name, date_time, db_user)
VALUES (
           TG_OP,
           TG_TABLE_NAME,
           now(),
           session_user
       );
RETURN NEW;
END;
$$ LANGUAGE plpgsql;


SELECT
    'CREATE TRIGGER trg_' || table_name || '_log AFTER INSERT OR UPDATE OR DELETE ON "' || table_name || '" ' ||
    'FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();'
FROM information_schema.tables
WHERE table_schema = 'public'
  AND table_type = 'BASE TABLE'
  AND table_name <> 'nbp_log';



--RESPONSE:--
CREATE TRIGGER trg_appointment_log AFTER INSERT OR UPDATE OR DELETE ON "appointment" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_role_log AFTER INSERT OR UPDATE OR DELETE ON "role" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_user_log AFTER INSERT OR UPDATE OR DELETE ON "user" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_hospital_log AFTER INSERT OR UPDATE OR DELETE ON "hospital" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_nbp_user_log AFTER INSERT OR UPDATE OR DELETE ON "nbp_user" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_doctor_log AFTER INSERT OR UPDATE OR DELETE ON "doctor" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_patient_log AFTER INSERT OR UPDATE OR DELETE ON "patient" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_medical_record_log AFTER INSERT OR UPDATE OR DELETE ON "medical_record" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_nbp_apps_log AFTER INSERT OR UPDATE OR DELETE ON "nbp_apps" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_service_log AFTER INSERT OR UPDATE OR DELETE ON "service" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_payment_log AFTER INSERT OR UPDATE OR DELETE ON "payment" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_medication_log AFTER INSERT OR UPDATE OR DELETE ON "medication" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_prescription_log AFTER INSERT OR UPDATE OR DELETE ON "prescription" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();
CREATE TRIGGER trg_insurance_log AFTER INSERT OR UPDATE OR DELETE ON "insurance" FOR EACH ROW EXECUTE FUNCTION log_all_table_changes();