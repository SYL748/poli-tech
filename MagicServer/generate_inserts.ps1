# Generate INSERT statements for voter_list from 19_fl_voter_list.csv
$outputPath = 'src\main\resources\db\migration\V26__seed_voter_list.sql'
$csvPath = 'src\main\resources\mock\split\26.csv'

# Remove output file if it exists
if (Test-Path $outputPath) {
    Remove-Item $outputPath
}

# Create file stream writer for better performance (buffered writes)
$writer = [System.IO.StreamWriter]::new($outputPath, $false, [System.Text.Encoding]::UTF8)

try {
    $count = 0
    Write-Host "Reading CSV file..."
    
    # Use Import-Csv but write directly to file instead of building array
    # This avoids the expensive array concatenation operation
    Import-Csv $csvPath | ForEach-Object {
        $regionId = [int]$_.region_id
        # Escape single quotes in name_full and party for SQL
        $nameFull = $_.name_full -replace "'", "''"
        $party = $_.party -replace "'", "''"
        
        # Handle NULL/empty values
        if ([string]::IsNullOrWhiteSpace($nameFull)) { 
            $nameFull = 'NULL' 
        }
        else { 
            $nameFull = "'$nameFull'" 
        }
        if ([string]::IsNullOrWhiteSpace($party)) { 
            $party = 'NULL' 
        }
        else { 
            $party = "'$party'" 
        }
        
        # Write directly to file instead of building array in memory
        $writer.WriteLine("INSERT INTO voter_list(region_id,name_full,party) VALUES ($regionId,$nameFull,$party);")
        
        $count++
        if ($count % 100000 -eq 0) {
            Write-Host "Processed $count rows..."
        }
    }
    
    Write-Host "Total rows processed: $count"
    Write-Host "Generated V23__seed_voter_list.sql with $count INSERT statements."
}
finally {
    $writer.Close()
    $writer.Dispose()
}
