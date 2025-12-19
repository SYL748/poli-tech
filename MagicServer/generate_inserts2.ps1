# Generate INSERT statements for equipment_summary_by_state from 6_Equipment_Summary_by_State_with_short_description.csv
# Note: This CSV has unquoted multi-word values, so we use a custom parser
$outputPath = 'src\main\resources\db\migration\V36__seed_equipment_summary_by_state.sql'
$csvPath = 'src\main\resources\mock\latest\6_Equipment_Summary_by_State_with_short_description.csv'

# Remove output file if it exists
if (Test-Path $outputPath) {
    Remove-Item $outputPath
}

Write-Host "Reading CSV file with custom parser..."

# Create file stream writer for better performance
$writer = [System.IO.StreamWriter]::new($outputPath, $false, [System.Text.Encoding]::UTF8)

try {
    $lines = Get-Content -Path $csvPath
    $count = 0
    
    # Skip header line (index 0)
    for ($i = 1; $i -lt $lines.Length; $i++) {
        $line = $lines[$i]
        
        # Skip empty lines
        if ([string]::IsNullOrWhiteSpace($line)) {
            continue
        }
        
        # Custom CSV parser that properly handles quoted values
        $values = @()
        $current = ""
        $inQuotes = $false
        
        for ($j = 0; $j -lt $line.Length; $j++) {
            $char = $line[$j]
            
            if ($char -eq '"') {
                $inQuotes = -not $inQuotes
            }
            elseif ($char -eq ',' -and -not $inQuotes) {
                $values += $current.Trim()
                $current = ""
            }
            else {
                $current += $char
            }
        }
        # Add the last value
        $values += $current.Trim()
        
        # Ensure we have at least 14 columns (CSV has an extra ID column at the beginning)
        if ($values.Count -lt 14) {
            Write-Host "Warning: Skipping line $($i+1) - only $($values.Count) columns found"
            continue
        }
        
        # Map values to columns based on CSV structure
        # Column 0 is an index/ID that we skip
        $stateId = $values[1]
        $manufacturer = $values[2]
        $modelName = $values[3]
        $quantity = $values[4]
        $equipmentType = $values[5]
        $age = $values[6]
        $os = $values[7]
        $certificationLevel = $values[8]
        $scanningRate = $values[9]
        $errorRate = $values[10]
        $reliability = $values[11]
        $quality = $values[12]
        $shortDescription = $values[13]
        
        # Skip if state_id is empty or invalid
        if ([string]::IsNullOrWhiteSpace($stateId)) {
            continue
        }
        
        try {
            # Format state_id with leading zeros (2 digits)
            $stateIdFormatted = "{0:D2}" -f [int]$stateId
        }
        catch {
            Write-Host "Warning: Skipping line $($i+1) - invalid state_id: $stateId"
            continue
        }
        
        # Escape single quotes in text fields
        if (-not [string]::IsNullOrWhiteSpace($manufacturer)) { $manufacturer = $manufacturer.Replace("'", "''") }
        if (-not [string]::IsNullOrWhiteSpace($modelName)) { $modelName = $modelName.Replace("'", "''") }
        if (-not [string]::IsNullOrWhiteSpace($equipmentType)) { $equipmentType = $equipmentType.Replace("'", "''") }
        if (-not [string]::IsNullOrWhiteSpace($os)) { $os = $os.Replace("'", "''") }
        if (-not [string]::IsNullOrWhiteSpace($certificationLevel)) { $certificationLevel = $certificationLevel.Replace("'", "''") }
        if (-not [string]::IsNullOrWhiteSpace($scanningRate)) { $scanningRate = $scanningRate.Replace("'", "''") }
        if (-not [string]::IsNullOrWhiteSpace($errorRate)) { $errorRate = $errorRate.Replace("'", "''") }
        if (-not [string]::IsNullOrWhiteSpace($shortDescription)) { $shortDescription = $shortDescription.Replace("'", "''") }
        
        # Handle NULL/empty values and proper quoting for SQL
        if ([string]::IsNullOrWhiteSpace($manufacturer)) { $manufacturer = 'NULL' } else { $manufacturer = "'$manufacturer'" }
        if ([string]::IsNullOrWhiteSpace($modelName)) { $modelName = 'NULL' } else { $modelName = "'$modelName'" }
        
        # Safely convert quantity to integer
        if ([string]::IsNullOrWhiteSpace($quantity)) { 
            $quantity = 0 
        } else { 
            try { 
                $quantity = [int]$quantity 
            } catch { 
                Write-Host "Warning: Invalid quantity '$quantity' on line $($i+1), setting to 0"
                $quantity = 0 
            }
        }
        
        if ([string]::IsNullOrWhiteSpace($equipmentType)) { $equipmentType = 'NULL' } else { $equipmentType = "'$equipmentType'" }
        
        # Safely convert age to decimal/NULL
        if ([string]::IsNullOrWhiteSpace($age)) { 
            $age = 'NULL' 
        } else {
            try {
                $ageNum = [decimal]$age
                $age = $ageNum.ToString([System.Globalization.CultureInfo]::InvariantCulture)
            } catch {
                $age = 'NULL'
            }
        }
        
        if ([string]::IsNullOrWhiteSpace($os)) { $os = 'NULL' } else { $os = "'$os'" }
        if ([string]::IsNullOrWhiteSpace($certificationLevel)) { $certificationLevel = 'NULL' } else { $certificationLevel = "'$certificationLevel'" }
        if ([string]::IsNullOrWhiteSpace($scanningRate)) { $scanningRate = 'NULL' } else { $scanningRate = "'$scanningRate'" }
        if ([string]::IsNullOrWhiteSpace($errorRate)) { $errorRate = 'NULL' } else { $errorRate = "'$errorRate'" }
        
        # Safely convert reliability and quality to decimal/NULL
        if ([string]::IsNullOrWhiteSpace($reliability)) { 
            $reliability = 'NULL' 
        } else {
            try {
                $relNum = [decimal]$reliability
                $reliability = $relNum.ToString([System.Globalization.CultureInfo]::InvariantCulture)
            } catch {
                $reliability = 'NULL'
            }
        }
        
        if ([string]::IsNullOrWhiteSpace($quality)) { 
            $quality = 'NULL' 
        } else {
            try {
                $qualNum = [decimal]$quality
                $quality = $qualNum.ToString([System.Globalization.CultureInfo]::InvariantCulture)
            } catch {
                $quality = 'NULL'
            }
        }
        
        if ([string]::IsNullOrWhiteSpace($shortDescription)) { $shortDescription = 'NULL' } else { $shortDescription = "'$shortDescription'" }
        
        # Generate INSERT statement with WHERE EXISTS check to only insert if state_id exists
        $insertStmt = "INSERT INTO equipment_summary_by_state (state_id, manufacturer, model_name, quantity, equipment_type, age, os, certification_level, scanning_rate, error_rate, reliability, quality, short_description)`r`n"
        $insertStmt += "SELECT $stateIdFormatted, $manufacturer, $modelName, $quantity, $equipmentType, $age, $os, $certificationLevel, $scanningRate, $errorRate, $reliability, $quality, $shortDescription`r`n"
        $insertStmt += "WHERE EXISTS (SELECT 1 FROM state WHERE state_id = $stateIdFormatted);"
        
        $writer.WriteLine($insertStmt)
        
        $count++
        if ($count % 50 -eq 0) {
            Write-Host "Processed $count rows..."
        }
    }
    
    Write-Host "Total rows processed: $count"
    Write-Host "Generated V36__seed_equipment_summary_by_state.sql with $count INSERT statements."
}
finally {
    $writer.Close()
    $writer.Dispose()
}
