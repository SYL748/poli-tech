INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 02, 'Dominion', 'ImageCast Central', 11, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 02);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 02, 'Dominion', 'ImageCast Precinct 2', 562, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 02);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 02, 'Dominion', 'ImageCast X as DRE', 145, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.95, 0.83, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 02);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'Dominion', 'ImageCast Central', 12, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'Dominion', 'ImageCast Precinct 2', 507, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'Dominion', 'ImageCast X as DRE', 224, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.95, 0.83, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'ES&S', 'DS200', 48, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'ES&S', 'DS300', 1, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'ES&S', 'DS450', 10, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'ES&S', 'DS850', 15, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'ES&S', 'DS950', 7, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'ES&S', 'ExpressTouch', 18, 'DRE Touchscreen', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.58, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'ES&S', 'ExpressVote', 910, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'ES&S', 'ExpressVote XL', 3, 'BMD/Tabulator', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.58, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'Unisyn', 'Freedom Vote Tablet (FVT)', 47, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.86, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 04, 'Unisyn', 'OpenElect (OVCS)', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 04);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 05, 'ES&S', 'DS200', 1087, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 05);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 05, 'ES&S', 'DS450', 3, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 05);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 05, 'ES&S', 'ExpressVote', 4584, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 05);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 05, 'ES&S', 'iVotronic', 318, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.65, 0.3, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 05);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'Avalue', 'SLD-21V-237-B1R', 603, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'Dominion', 'Democracy Suite 5.10-A', 16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'Dominion', 'ImageCast Central', 210, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'Dominion', 'ImageCast Evolution', 136, 'Hybrid Optical Scanner/BMD', 14.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.57, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'Dominion', 'ImageCast Precinct 2', 425, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'Dominion', 'ImageCast X', 9114, 'BMD', 12.0, 'Android', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.67, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'ES&S', 'DS200', 13, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'ES&S', 'DS300', 28, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'ES&S', 'DS450', 4, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'ES&S', 'DS950', 0, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'ES&S', 'ExpressVote', 72, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'Hart InterCivic', 'Verity Central', 54, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'Hart InterCivic', 'Verity Scan', 654, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'Hart InterCivic', 'Verity Touch Writer', 1175, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'Smartmatic/Los Angeles County', 'IBML', 22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 06, 'Smartmatic/Los Angeles County', 'VSAP BMD', 11144, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.45, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 06);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 08, 'Clear Ballot', 'ClearAccess', 79, 'BMD', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.79, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 08);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 08, 'Clear Ballot', 'ClearCount', 10, 'Batch-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.77, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 08);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 08, 'Dominion', 'ImageCast Central', 172, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 08);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 08, 'Dominion', 'ImageCast X', 1466, 'BMD', 12.0, 'Android', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.67, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 08);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 09, 'IVS', 'Inspire Ballot Marking Device', 718, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.77, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 09);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 09, 'Premier Election Solutions (Diebold)', 'AccuVote OS', 1436, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.44, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 09);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 10, 'ES&S', 'DS450', 10, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 10);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 10, 'ES&S', 'ExpressVote XL', 1353, 'BMD/Tabulator', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.58, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 10);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 11, 'ES&S', 'DS200', 103, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 11);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 11, 'ES&S', 'ExpressVote', 337, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 11);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 12, 'Clear Ballot', 'ClearCount', 6, 'Batch-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.77, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 12);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 12, 'Dominion', 'ImageCast Central', 18, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 12);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 12, 'Dominion', 'ImageCast Evolution', 1113, 'Hybrid Optical Scanner/BMD', 14.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.57, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 12);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 12, 'ES&S', 'DS200', 5205, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 12);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 12, 'ES&S', 'DS300', 2275, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 12);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 12, 'ES&S', 'DS450', 8, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 12);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 12, 'ES&S', 'DS850', 64, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 12);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 12, 'ES&S', 'DS950', 37, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 12);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 12, 'ES&S', 'ExpressVote', 5148, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 12);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 12, 'ES&S', 'ExpressVote XL', 8, 'BMD/Tabulator', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.58, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 12);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 13, 'Dominion', 'ImageCast Central', 172, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 13);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 13, 'Dominion', 'ImageCast Precinct', 2601, 'Hand-Fed Optical Scanner', 14.0, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 13);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 13, 'Dominion', 'ImageCast X', 19441, 'BMD', 12.0, 'Android', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.67, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 13);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 15, 'Hart InterCivic', 'Verity Central', 13, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 15);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 15, 'Hart InterCivic', 'Verity Scan', 33, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 15);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 15, 'Hart InterCivic', 'Verity Touch Writer', 62, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 15);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 16, 'ES&S', 'DS200', 179, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 16);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 16, 'ES&S', 'DS300', 48, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 16);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 16, 'ES&S', 'DS450', 9, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 16);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 16, 'ES&S', 'DS850', 4, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 16);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 16, 'ES&S', 'ExpressVote', 571, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 16);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 16, 'Hart InterCivic', 'Verity Central', 5, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 16);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 16, 'Hart InterCivic', 'Verity Scan', 479, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 16);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 16, 'Hart InterCivic', 'Verity Touch Writer', 667, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 16);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Dominion', 'AutoMARK', 113, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Dominion', 'ImageCast Precinct', 1408, 'Hand-Fed Optical Scanner', 14.0, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Dominion', 'ImageCast Precinct 2', 1500, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Dominion', 'ImageCast X', 5285, 'BMD', 12.0, 'Android', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.67, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'ES&S', 'DS200', 1622, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'ES&S', 'DS450', 3, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'ES&S', 'DS850', 1, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'ES&S', 'ExpressVote', 2069, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'ES&S', 'M100', 49, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Hart InterCivic', 'Veracity', 71, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Hart InterCivic', 'Verity 2.7', 1275, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Hart InterCivic', 'Verity Scan', 2361, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Hart InterCivic', 'Verity Touch Writer', 1023, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Premier Election Solutions (Diebold)', 'AccuVote OS', 68, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.44, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Premier Election Solutions (Diebold)', 'AccuVote TSX', 34, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.7, 0.34, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Premier Election Solutions (Diebold)', 'TSX', 72, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Unisyn', 'AccuVote', 31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Unisyn', 'Freedom Vote Tablet (FVT)', 1004, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.86, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Unisyn', 'OpenElect OVI', 29, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.77, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 17, 'Unisyn', 'OpenElect OVO', 684, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.8, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 17);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'Dominion', 'AutoMARK', 62, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'ES&S', 'DS200', 774, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'ES&S', 'ExpressVote', 3399, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'ES&S', 'M100', 20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'Hart', 'InterCivic', 178, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'Hart InterCivic', 'Verity Duo', 43, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.55, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'Hart InterCivic', 'Verity Scan', 111, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'Hart InterCivic', 'Verity Touch Writer', 68, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'Hart InterCivic', 'Verity Voting 2.5', 300, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'MicroVote', 'Chatsworth ACP', 66, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'MicroVote', 'EMS', 7771, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'Unisyn', 'Freedom Vote Scan (FVS)', 43, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 1.0, 0.85, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'Unisyn', 'Freedom Vote Tablet (FVT)', 1507, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.86, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 18, 'Unisyn', 'OpenElect OVO', 152, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.8, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 18);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 21, 'ES&S', 'DS200', 932, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 21);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 21, 'ES&S', 'DS300', 5, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 21);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 21, 'ES&S', 'DS450', 3, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 21);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 21, 'ES&S', 'ExpressVote', 1499, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 21);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 21, 'Hart', 'InterCivic', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 21);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 21, 'Hart InterCivic', 'Verity Duo', 565, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.55, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 21);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 21, 'Hart InterCivic', 'Verity Scan', 2150, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 21);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 21, 'Hart InterCivic', 'Verity Touch Writer', 741, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 21);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 21, 'Hart InterCivic', 'eScan', 5, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.83, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 21);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2140', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2141', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2142', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2143', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2144', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2145', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2146', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2147', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2148', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2149', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2150', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2151', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2152', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2153', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2154', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2155', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2156', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2157', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2158', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2159', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2160', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2161', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2162', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2163', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2164', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2165', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2166', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2167', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2168', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2169', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2170', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2171', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2172', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2173', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2174', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2175', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2176', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2177', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2178', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2179', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2180', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2181', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2182', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2183', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2184', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2185', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2186', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2187', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2188', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2189', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2190', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2191', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2192', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2193', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2194', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2195', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2196', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2197', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2198', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2199', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2200', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2201', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2202', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Canon', 'DR-G2203', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Dominion', 'ImageCast X as DRE', 801, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.95, 0.83, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 22, 'Sequoia Voting Systems', 'AV', 8734, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 22);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 23, 'ES&S', 'DS200', 600, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 23);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 23, 'ES&S', 'ExpressVote', 516, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 23);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 24, 'ES&S', 'DS200', 0, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 24);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 24, 'ES&S', 'DS850', 0, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 24);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 24, 'ES&S', 'ExpressVote', 6104, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 24);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 25, 'Dominion', 'AutoMARK', 1671, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 25);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 25, 'Dominion', 'ImageCast Central', 55, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 25);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 25, 'Dominion', 'ImageCast Precinct', 1789, 'Hand-Fed Optical Scanner', 14.0, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 25);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 25, 'Dominion', 'ImageCast Precinct 2', 534, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 25);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 25, 'ES&S', 'DS200', 528, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 25);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 25, 'ES&S', 'DS450', 6, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 25);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 25, 'ES&S', 'DS850', 1, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 25);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 25, 'Premier Election Solutions (Diebold)', 'AccuVote OS', 53, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.44, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 25);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 26, 'Dominion', 'ImageCast Precinct', 0, 'Hand-Fed Optical Scanner', 14.0, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 26);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 26, 'Dominion', 'ImageCast X', 2632, 'BMD', 12.0, 'Android', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.67, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 26);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 26, 'ES&S', 'DS200', 0, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 26);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 26, 'ES&S', 'ExpressVote', 551, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 26);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 26, 'Hart InterCivic', 'Verity Scan', 0, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 26);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 26, 'Hart InterCivic', 'Verity Touch Writer', 1157, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 26);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 27, 'Democracy Live', 'OmniBallot Tablet', 1911, 'BMD', NULL, 'Windows 10 IoT Enterprise', NULL, NULL, NULL, 0.7, 0.45, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 27);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 27, 'Dominion', 'AutoMARK', 459, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 27);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 27, 'Dominion', 'ImageCast Evolution', 580, 'Hybrid Optical Scanner/BMD', 14.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.57, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 27);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 27, 'ES&S', 'DS200', 2423, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 27);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 27, 'ES&S', 'DS450', 29, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 27);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 27, 'ES&S', 'DS850', 5, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 27);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 27, 'ES&S', 'DS950', 11, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 27);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 27, 'ES&S', 'ExpressVote', 120, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 27);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 27, 'Hart InterCivic', 'Verity Central', 12, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 27);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 27, 'Hart InterCivic', 'Verity Scan', 196, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 27);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 27, 'Hart InterCivic', 'Verity Touch Writer', 186, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 27);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 28, 'ES&S', 'DS200', 0, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 28);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 28, 'ES&S', 'ExpressVote', 0, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 28);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 28, 'Hart InterCivic', 'Verity Scan', 0, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 28);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 28, 'Hart InterCivic', 'Verity Touch Writer', 0, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 28);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 28, 'VotingWorks', 'Unknown', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 28);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'Dominion', 'ImageCast Precinct', 359, 'Hand-Fed Optical Scanner', 14.0, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'Dominion', 'ImageCast X', 231, 'BMD', 12.0, 'Android', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.67, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'ES&S', 'DS200', 333, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'ES&S', 'DS300', 113, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'ES&S', 'DS450', 7, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'ES&S', 'ExpressVote', 632, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'Hart InterCivic', 'Verity Scan', 573, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'Hart InterCivic', 'Verity Touch Writer', 297, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'Unisyn', 'Freedom Vote Scan (FVS)', 699, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 1.0, 0.85, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'Unisyn', 'Freedom Vote Tablet (FVT)', 868, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.86, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'Unisyn', 'OpenElect (OVCS)', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'Unisyn', 'OpenElect OVI', 261, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.77, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 29, 'Unisyn', 'OpenElect OVO', 556, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.8, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 29);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 30, 'ES&S', 'DS200', 82, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 30);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 30, 'ES&S', 'DS300', 17, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 30);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 30, 'ES&S', 'DS450', 16, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 30);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 30, 'ES&S', 'DS850', 22, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 30);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 30, 'ES&S', 'DS950', 4, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 30);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 30, 'ES&S', 'ExpressVote', 367, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 30);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 31, 'ES&S', 'DS200', 70, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 31);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 31, 'ES&S', 'DS450', 70, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 31);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 31, 'ES&S', 'DS850', 19, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 31);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 31, 'ES&S', 'ExpressVote', 1284, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 31);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 32, 'Dominion', 'ImageCast Central', 30, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 32);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 32, 'Dominion', 'ImageCast Precinct 2', 3, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 32);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 32, 'Dominion', 'ImageCast X', 41, 'BMD', 12.0, 'Android', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.67, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 32);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 32, 'Dominion', 'ImageCast X as DRE', 5035, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.95, 0.83, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 32);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 32, 'ES&S', 'DS200', 6, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 32);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 32, 'ES&S', 'DS450', 2, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 32);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 32, 'ES&S', 'DS950', 1, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 32);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 32, 'ES&S', 'ExpressVote', 80, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 32);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 32, 'InterScan', 'HiPro 8x1', 6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 32);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 33, 'Premier Election Solutions (Diebold)', 'AccuVote OS', 265, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.44, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 33);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 33, 'VotingWorks', 'VXSUITE 3.1.2', 24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 33);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 34, 'Dominion', 'ImageCast Central', 24, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 34);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 34, 'Dominion', 'ImageCast X', 3202, 'BMD', 12.0, 'Android', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.67, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 34);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 34, 'ES&S', 'DS450', 20, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 34);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 34, 'ES&S', 'DS850', 7, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 34);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 34, 'ES&S', 'DS950', 14, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 34);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 34, 'ES&S', 'ExpressVote', 347, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 34);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 34, 'ES&S', 'ExpressVote XL', 7612, 'BMD/Tabulator', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.58, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 34);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 35, 'Dominion', 'ImageCast Central', 48, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 35);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 35, 'Dominion', 'ImageCast Evolution', 1527, 'Hybrid Optical Scanner/BMD', 14.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.57, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 35);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 36, 'Clear Ballot', 'ClearCast', 951, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.71, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 36);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 36, 'Clear Ballot', 'ClearMark', 681, 'BMD', NULL, NULL, 'NYSBOE (2023)', NULL, NULL, 1.0, 0.72, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 36);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 36, 'Dominion', 'AutoMARK', 2161, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 36);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 36, 'Dominion', 'ImageCast Evolution', 5923, 'Hybrid Optical Scanner/BMD', 14.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.57, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 36);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 36, 'Dominion', 'ImageCast Precinct', 115, 'Hand-Fed Optical Scanner', 14.0, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 36);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 36, 'Dominion', 'ImageCast Precinct 2', 24, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 36);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 36, 'ES&S', 'DS200', 5579, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 36);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 36, 'ES&S', 'DS300', 276, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 36);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 36, 'Hart InterCivic', 'Verity Scan', 263, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 36);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 36, 'Hart InterCivic', 'Verity Touch Writer', 202, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 36);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 37, 'Dominion', 'AutoMARK', 604, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 37);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 37, 'ES&S', 'DS200', 3165, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 37);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 37, 'ES&S', 'DS300', 93, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 37);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 37, 'ES&S', 'DS450', 28, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 37);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 37, 'ES&S', 'DS850', 33, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 37);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 37, 'ES&S', 'ExpressVote', 5406, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 37);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 37, 'ES&S', 'M100', 43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 37);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 37, 'Hart InterCivic', 'Verity Central', 6, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 37);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 37, 'Hart InterCivic', 'Verity Scan', 215, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 37);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 37, 'Hart InterCivic', 'Verity Touch Writer', 182, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 37);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 38, 'ES&S', 'DS200', 453, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 38);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 38, 'ES&S', 'DS450', 57, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 38);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 38, 'ES&S', 'ExpressVote', 429, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 38);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Clear Ballot', 'ClearAccess', 574, 'BMD', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.79, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Clear Ballot', 'ClearCast', 1607, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.71, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Clear Ballot', 'ClearCount', 53, 'Batch-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.77, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Dominion', 'AutoMARK', 21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Dominion', 'ImageCast Central', 24, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Dominion', 'ImageCast Evolution', 40, 'Hybrid Optical Scanner/BMD', 14.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.57, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Dominion', 'ImageCast Precinct', 116, 'Hand-Fed Optical Scanner', 14.0, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Dominion', 'ImageCast X as DRE', 5826, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.95, 0.83, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'ES&S', 'DS200', 2729, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'ES&S', 'DS300', 11, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'ES&S', 'DS450', 29, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'ES&S', 'DS850', 5, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'ES&S', 'DS950', 4, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'ES&S', 'ExpressVote', 10560, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'ES&S', 'ExpressVote XL', 11, 'BMD/Tabulator', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.58, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Fujitsu', 'fi-7900', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Hart InterCivic', 'Verity Central', 5, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Hart InterCivic', 'Verity Scan', 596, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Hart InterCivic', 'Verity Touch Writer', 313, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Unisyn', 'Freedom Vote Scan (FVS)', 12, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 1.0, 0.85, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Unisyn', 'Freedom Vote Tablet (FVT)', 1925, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.86, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Unisyn', 'OpenElect (OVCS)', 45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Unisyn', 'OpenElect OVI', 30, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.77, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 39, 'Unisyn', 'OpenElect OVO', 223, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.8, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 39);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 40, 'Hart InterCivic', 'Verity Central', 4, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 40);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 40, 'Hart InterCivic', 'eScan', 2067, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.83, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 40);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 41, 'Clear Ballot', 'ClearCount', 0, 'Batch-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.77, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 41);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 41, 'ES&S', 'DS450', 0, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 41);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 41, 'ES&S', 'DS850', 0, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 41);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 41, 'ES&S', 'M650', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 41);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 41, 'Hart InterCivic', 'Verity Central', 0, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 41);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Clear Ballot', 'ClearAccess', 521, 'BMD', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.79, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Clear Ballot', 'ClearCast', 840, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.71, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Clear Ballot', 'ClearCount', 17, 'Batch-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.77, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Dominion', 'ImageCast Central', 25, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Dominion', 'ImageCast Precinct', 1058, 'Hand-Fed Optical Scanner', 14.0, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Dominion', 'ImageCast X', 1636, 'BMD', 12.0, 'Android', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.67, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'ES&S', 'DS200', 4079, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'ES&S', 'DS450', 37, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'ES&S', 'DS850', 11, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'ES&S', 'DS950', 2, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'ES&S', 'ExpressVote', 6077, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'ES&S', 'ExpressVote XL', 8202, 'BMD/Tabulator', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.58, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Hart InterCivic', 'Verity Central', 7, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Hart InterCivic', 'Verity Scan', 442, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Hart InterCivic', 'Verity Touch Writer', 441, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Unisyn', 'Freedom Vote Tablet (FVT)', 552, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.86, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Unisyn', 'OpenElect (OVCS)', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 42, 'Unisyn', 'OpenElect OVO', 128, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.8, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 42);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 44, 'ES&S', 'DS200', 590, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 44);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 44, 'ES&S', 'DS850', 117, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 44);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 44, 'ES&S', 'ExpressVote', 495, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 44);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 45, 'ES&S', 'DS300', 3124, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 45);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 45, 'ES&S', 'DS450', 21, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 45);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 45, 'ES&S', 'DS850', 1, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 45);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 45, 'ES&S', 'ExpressVote', 15659, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 45);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 46, 'Dominion', 'AutoMARK', 17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 46);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 46, 'ES&S', 'DS200', 32, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 46);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 46, 'ES&S', 'DS450', 25, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 46);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 46, 'ES&S', 'DS850', 14, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 46);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 46, 'ES&S', 'ExpressVote', 420, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 46);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'ES&S', 'DS200', 955, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'ES&S', 'DS300', 117, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'ES&S', 'DS450', 10, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'ES&S', 'DS950', 1, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'ES&S', 'ExpressVote', 3595, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'Hart InterCivic', 'Verity Central', 3, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'Hart InterCivic', 'Verity Duo', 254, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.55, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'Hart InterCivic', 'Verity Scan', 535, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'Hart InterCivic', 'Verity Touch Writer', 716, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'MicroVote', 'Infinity', 2930, 'DRE Push Button', NULL, 'Microsoft Windows 2000, Windows XP Professional', NULL, NULL, NULL, 0.95, 0.48, 'Push-button DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'Unisyn', 'Freedom Vote Tablet (FVT)', 56, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.86, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'Unisyn', 'OpenElect OVI', 11, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.77, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 47, 'Unisyn', 'OpenElect OVO', 11, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.8, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 47);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Dominion', 'AutoMARK', 84, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'ES&S', 'DS200', 3026, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'ES&S', 'DS300', 78, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'ES&S', 'DS450', 38, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'ES&S', 'DS850', 7, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'ES&S', 'DS950', 4, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'ES&S', 'ExpressTouch', 733, 'DRE Touchscreen', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.58, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'ES&S', 'ExpressVote', 17186, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'ES&S', 'ExpressVote XL', 8, 'BMD/Tabulator', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.58, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'ES&S', 'M100', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'ES&S', 'M650', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'ES&S', 'iVotronic', 63, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.65, 0.3, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Hart InterCivic', 'Verity Central', 34, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Hart InterCivic', 'Verity Controller', 17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Hart InterCivic', 'Verity Duo', 16542, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.55, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Hart InterCivic', 'Verity Duo Go', 120, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Hart InterCivic', 'Verity Scan', 5138, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Hart InterCivic', 'Verity Touch', 59, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.95, 0.65, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Hart InterCivic', 'Verity Touch Writer', 12030, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Hart InterCivic', 'Verity Voting 2.5', 40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Hart InterCivic', 'eScan', 133, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.83, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Hart InterCivic', 'eSlate', 175, 'DRE Dial', NULL, NULL, NULL, NULL, NULL, 0.95, 0.8, 'Dial-based DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Kodak', 'I260', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 48, 'Kodak', 'I620', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 48);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 49, 'Dominion', 'ImageCast Central', 9, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 49);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 49, 'Dominion', 'ImageCast X as DRE', 665, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.95, 0.83, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 49);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 49, 'ES&S', 'DS200', 39, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 49);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 49, 'ES&S', 'DS300', 26, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 49);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 49, 'ES&S', 'DS450', 20, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 49);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 49, 'ES&S', 'DS850', 2, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 49);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 49, 'ES&S', 'ExpressVote', 169, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 49);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 49, 'Premier Election Solutions (Diebold)', 'AccuVote TSX', 2, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.7, 0.34, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 49);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 49, 'Unisyn', 'OpenElect OVO', 1, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.8, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 49);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 50, 'Democracy Live', 'OmniBallot Tablet', 262, 'BMD', NULL, 'Windows 10 IoT Enterprise', NULL, NULL, NULL, 0.7, 0.45, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 50);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'Dominion', 'ImageCast Evolution', 756, 'Hybrid Optical Scanner/BMD', 14.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.57, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'Dominion', 'ImageCast Precinct', 23, 'Hand-Fed Optical Scanner', 14.0, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'Dominion', 'ImageCast Precinct 2', 21, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.51, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'ES&S', 'DS200', 1862, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'ES&S', 'DS300', 132, 'Hand-Fed Optical Scanner', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'ES&S', 'DS450', 5, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'ES&S', 'DS850', 2, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'ES&S', 'ExpressVote', 1699, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'Hart InterCivic', 'Verity Central', 5, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'Hart InterCivic', 'Verity Scan', 586, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.79, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'Hart InterCivic', 'Verity Touch Writer', 413, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'Unisyn', 'Freedom Vote Scan (FVS)', 349, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 1.0, 0.85, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'Unisyn', 'Freedom Vote Tablet (FVT)', 130, 'BMD', NULL, NULL, NULL, NULL, NULL, 1.0, 0.86, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'Unisyn', 'OpenElect (OVCS)', 5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'Unisyn', 'OpenElect OVI', 453, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.77, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 51, 'Unisyn', 'OpenElect OVO', 414, 'Hand-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.8, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 51);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 53, 'Clear Ballot', 'ClearAccess', 48, 'BMD', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.79, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 53);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 53, 'Clear Ballot', 'ClearCount', 47, 'Batch-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.77, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 53);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 53, 'Dominion', 'ImageCast Central', 2, 'Batch-Fed Optical Scanner', 14.0, 'Windows', 'VVSG 1.0 (2005)', 'High-speed', '<=1/500,000', 0.75, 0.57, 'High-speed central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 53);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 53, 'Dominion', 'ImageCast X', 1, 'BMD', 12.0, 'Android', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.67, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 53);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 53, 'ES&S', 'DS450', 2, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 53);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 53, 'ES&S', 'DS850', 4, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 53);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 53, 'ES&S', 'DS950', 2, 'Batch-Fed Optical Scanner', NULL, NULL, '(VVSG) 2.0', '280 ballots/minute', '<=1/1,670,000', 0.7, 0.66, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 53);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 53, 'ES&S', 'ExpressVote', 12, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 53);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 53, 'ES&S', 'ExpressVote XL', 9, 'BMD/Tabulator', NULL, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.7, 0.58, 'Hybrid polling-place device for ballot marking and paper-ballot scanning/tabulation.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 53);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 53, 'Hart InterCivic', 'Verity Central', 28, 'Batch-Fed Optical Scanner', NULL, NULL, NULL, NULL, NULL, 0.95, 0.69, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 53);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 53, 'Hart InterCivic', 'Verity Touch Writer', 24, 'BMD', NULL, NULL, NULL, NULL, NULL, 0.95, 0.73, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 53);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 54, 'ES&S', 'DS200', 1575, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 54);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 54, 'ES&S', 'DS450', 3, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 54);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 54, 'ES&S', 'DS850', 1, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 54);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 54, 'ES&S', 'ExpressVote', 5971, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 54);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 54, 'ES&S', 'M650', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nan voting equipment.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 54);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 55, 'Clear Ballot', 'ClearAccess', 0, 'BMD', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.79, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 55);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 55, 'Clear Ballot', 'ClearCast', 0, 'Hand-Fed Optical Scanner', NULL, NULL, 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 1.0, 0.71, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 55);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 55, 'Dominion', 'ImageCast X', 0, 'BMD', 12.0, 'Android', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.75, 0.67, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 55);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 55, 'Dominion', 'ImageCast X as DRE', 0, 'DRE Touchscreen', NULL, NULL, NULL, NULL, NULL, 0.95, 0.83, 'Touchscreen DRE voting machine.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 55);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 55, 'ES&S', 'DS200', 0, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 55);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 55, 'ES&S', 'DS450', 0, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 55);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 55, 'ES&S', 'DS850', 0, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 55);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 56, 'ES&S', 'DS200', 383, 'Hand-Fed Optical Scanner', 15.0, 'Windows', 'VVSG 1.0 (2005)', 'Highspeed', '<=1/500,000', 0.75, 0.57, 'Precinct optical scanner/tabulator for paper ballots (polling place).'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 56);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 56, 'ES&S', 'DS450', 10, 'Batch-Fed Optical Scanner', 9.0, 'Windows', 'VVSG 1.0 (2005)', '72 ballots/minute', '<=1/500,000', 0.85, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 56);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 56, 'ES&S', 'DS850', 1, 'Batch-Fed Optical Scanner', 13.0, 'Windows', 'VVSG 1.0 (2005)', '300 ballots/min', '<=1/500,000', 0.75, 0.64, 'central-count batch optical scanner for absentee/mail paper ballots.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 56);
INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)
SELECT 56, 'ES&S', 'ExpressVote', 510, 'BMD', 11.0, 'Windows', 'VVSG 1.0 (2005)', NULL, '<=1/500,000', 0.85, 0.62, 'Accessible ballot-marking device that prints a marked paper ballot.'
WHERE EXISTS (SELECT 1 FROM state WHERE state_id = 56);
