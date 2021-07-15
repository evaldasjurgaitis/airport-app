import React, { useEffect, useState } from 'react';
import axios from 'axios';

import Row from '../../../../shared/Row/Row';
import Col from '../../../../shared/Col/Col';
import Select, { Option } from '../../../../shared/Select/Select';

const AirportSearchForm = () => {
    const [countries, setCountries] = useState<Option[]>([]);
    const [selectedCountry, setSelectedCountry] = useState<string>('');

    useEffect(() => {
        axios.get(`http://localhost:8081/api/countries`).then((response) => {
            setCountries(
                response.data.map((country: { code: string; name: string }) => ({
                    value: country.code,
                    label: country.name,
                }))
            );
        });
    }, [setCountries]);

    return (
        <div>
            <Row>
                <Col span={6} xs={24} sm={10} md={10} xl={10}>
                    <Select
                        showSearch
                        options={countries}
                        onChange={(value) => {
                            setSelectedCountry(value);
                        }}
                        placeholder="Select country"
                    />
                </Col>
            </Row>
        </div>
    );
};

export default AirportSearchForm;
