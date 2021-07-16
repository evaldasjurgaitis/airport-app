import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import axios from 'axios';
import queryString from 'query-string';

import Row from '../../../../shared/Row/Row';
import Col from '../../../../shared/Col/Col';
import Select, { Option } from '../../../../shared/Select/Select';

interface Props {
    selectedCountry: string | undefined;
    setSelectedCountry: Dispatch<SetStateAction<string | undefined>>;
    selectedRegion: string | undefined;
    setSelectedRegion: Dispatch<SetStateAction<string | undefined>>;
}

const AirportSearchForm = ({ selectedCountry, setSelectedCountry, selectedRegion, setSelectedRegion }: Props) => {
    const [countries, setCountries] = useState<Option[]>([]);
    const [regions, setRegions] = useState<Option[]>([]);

    function getUrl(selectedCountry: string) {
        return queryString.stringifyUrl({
            url: 'http://localhost:8081/api/regions',
            query: {
                isoCountry: selectedCountry,
            },
        });
    }

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

    const onCountryChange = (value: string) => {
        setSelectedRegion(undefined);
        setSelectedCountry(value);
        axios.get(getUrl(value)).then((response) => {
            setRegions(
                response.data.map((region: { code: string; name: string }) => ({
                    value: region.code,
                    label: region.name,
                }))
            );
        });
    };

    return (
        <div>
            <Row gutter={[16, { xs: 8, sm: 16, md: 24, lg: 32 }]}>
                <Col span={6} xs={24} sm={8} md={8} xl={4}>
                    <Select
                        value={selectedCountry}
                        showSearch
                        options={countries}
                        onChange={onCountryChange}
                        placeholder="Select country"
                    />
                </Col>
                <Col span={6} xs={24} sm={8} md={8} xl={4}>
                    <Select
                        value={selectedRegion}
                        disabled={!selectedCountry}
                        showSearch
                        options={regions}
                        onChange={(value) => {
                            setSelectedRegion(value);
                        }}
                        placeholder="Select region"
                    />
                </Col>
            </Row>
        </div>
    );
};

export default AirportSearchForm;
